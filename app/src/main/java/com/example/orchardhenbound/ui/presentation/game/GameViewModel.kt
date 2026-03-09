package com.example.orchardhenbound.ui.presentation.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orchardhenbound.data.repository.RecordsRepository
import com.example.orchardhenbound.domain.model.FallingItem
import com.example.orchardhenbound.domain.model.ItemType
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.random.Random

class GameViewModel(
    private val recordsRepository: RecordsRepository
) : ViewModel() {

    private val maxLives = 3
    private val goodItemProbability = 0.75f
    private val spawnIntervalSec = 0.65f

    private val baseW = 412f
    private val baseH = 892f
    private val playerYRatio = 699f / baseH
    private val initialPlayerXRatio = 34f / baseW

    private val _state = MutableStateFlow(GameUiState(lives = maxLives))
    val state: StateFlow<GameUiState> = _state.asStateFlow()

    private var loopJob: Job? = null

    private var screenWpx: Float = 0f
    private var screenHpx: Float = 0f
    private var itemSizePx: Float = 0f
    private var playerWidthPx: Float = 0f
    private var playerHeightPx: Float = 0f

    private var spawnAccSec: Float = 0f

    fun onScreenMetrics(
        wPx: Float,
        hPx: Float,
        itemSizePx: Float,
        playerWidthPx: Float,
        playerHeightPx: Float
    ) {
        this.screenWpx = wPx
        this.screenHpx = hPx
        this.itemSizePx = itemSizePx
        this.playerWidthPx = playerWidthPx
        this.playerHeightPx = playerHeightPx

        _state.update { s ->
            val initX = (wPx * initialPlayerXRatio)
                .coerceIn(0f, max(0f, wPx - playerWidthPx))
            s.copy(playerX = initX)
        }
    }

    fun startLoop() {
        if (loopJob?.isActive == true) return

        loopJob = viewModelScope.launch {
            var lastTime = System.nanoTime()

            while (true) {
                val now = System.nanoTime()
                val dtSec = (now - lastTime) / 1_000_000_000f
                lastTime = now

                val s = _state.value
                if (!s.isPaused && !s.isGameOver) {
                    tick(dtSec)
                }

                delay(16L)
            }
        }
    }

    private var prePauseState: Boolean = false
    fun pause() {
        val s = _state.value
        if (!s.isPaused && !s.isGameOver) {
            prePauseState = true
            _state.update { it.copy(isPaused = true) }
        }
    }

    fun resume() {
        if (_state.value.isPaused) {
            _state.update { it.copy(isPaused = false) }
            prePauseState = false
        }
    }
    fun onDrag(deltaX: Float) {
        val s = _state.value
        if (s.isPaused || s.isGameOver) return
        if (screenWpx <= 0f) return

        val nextX = (s.playerX + deltaX).coerceIn(0f, max(0f, screenWpx - playerWidthPx))
        val facingRight = when {
            deltaX > 0.5f -> true
            deltaX < -0.5f -> false
            else -> s.facingRight
        }

        _state.update { it.copy(playerX = nextX, facingRight = facingRight) }
    }

    fun playAgain() {
        _state.update { old ->
            val initX = (screenWpx * initialPlayerXRatio)
                .coerceIn(0f, max(0f, screenWpx - playerWidthPx))
            spawnAccSec = 0f
            old.copy(
                isPaused = false,
                isGameOver = false,
                isScoreSaved = false,
                lives = maxLives,
                score = 0,
                items = emptyList(),
                facingRight = true,
                playerX = initX
            )
        }
    }
    fun exitToMenuSaveScoreIfNeeded() {
        saveScoreIfNeeded()
    }

    private fun saveScoreIfNeeded() {
        val s = _state.value
        if (s.score <= 0 || s.isScoreSaved) return

        _state.update { it.copy(isScoreSaved = true) }

        viewModelScope.launch {
            recordsRepository.addScore(s.score)
        }
    }

    private fun tick(dtSec: Float) {
        spawnAccSec += dtSec
        if (spawnAccSec >= spawnIntervalSec) {
            spawnAccSec = 0f
            spawnItem()
        }

        moveItems(dtSec)
        resolveCollisions()
    }

    private fun spawnItem() {
        if (screenWpx <= 0f || itemSizePx <= 0f) return

        val type = if (Random.nextFloat() < goodItemProbability) {
            listOf(ItemType.GOOD_1, ItemType.GOOD_2, ItemType.GOOD_3, ItemType.GOOD_4, ItemType.GOOD_5).random()
        } else {
            listOf(ItemType.BAD_1, ItemType.BAD_2).random()
        }

        val x = Random.nextFloat() * max(0f, (screenWpx - itemSizePx))
        val speedFraction = 0.2f + Random.nextFloat() * 0.18f
        val adaptiveSpeedPxPerSec = screenHpx * speedFraction

        val item = FallingItem(
            id = System.nanoTime(),
            type = type,
            xPx = x,
            yPx = -itemSizePx,
            speedPxPerSec = adaptiveSpeedPxPerSec
        )

        _state.update { it.copy(items = it.items + item) }
    }

    private fun moveItems(dtSec: Float) {
        val s = _state.value
        if (s.items.isEmpty()) return

        val moved = s.items.mapNotNull { item ->
            val newY = item.yPx + item.speedPxPerSec * dtSec
            if (newY > screenHpx) null else item.copy(yPx = newY)
        }

        if (moved != s.items) {
            _state.update { it.copy(items = moved) }
        }
    }

    private fun resolveCollisions() {
        val s = _state.value
        if (s.items.isEmpty() || s.isGameOver) return

        val playerY = screenHpx * playerYRatio
        val (collided, remaining) = s.items.partition { item ->
            overlaps(
                itemX = item.xPx, itemY = item.yPx, itemSize = itemSizePx,
                playerX = s.playerX, playerY = playerY,
                playerW = playerWidthPx, playerH = playerHeightPx
            )
        }

        if (collided.isEmpty()) return

        var newScore = s.score
        var newLives = s.lives
        var newGameOver = s.isGameOver

        collided.forEach { item ->
            when (item.type) {
                ItemType.BAD_1, ItemType.BAD_2 -> {
                    newLives -= 1
                    if (newLives <= 0) newGameOver = true
                }
                else -> newScore += 1
            }
        }

        _state.update {
            it.copy(
                items = remaining,
                score = newScore,
                lives = newLives.coerceAtLeast(0),
                isGameOver = newGameOver
            )
        }

        if (newGameOver) {
            saveScoreIfNeeded()
        }
    }

    private fun overlaps(
        itemX: Float,
        itemY: Float,
        itemSize: Float,
        playerX: Float,
        playerY: Float,
        playerW: Float,
        playerH: Float
    ): Boolean {
        val rightA = itemX + itemSize
        val bottomA = itemY + itemSize

        val rightB = playerX + playerW
        val bottomB = playerY + playerH

        return (rightA > playerX && itemX < rightB) && (bottomA > playerY && itemY < bottomB)
    }
}