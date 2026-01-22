package com.example.orchardhenbound.presentation.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orchardhenbound.domain.repository.RecordsRepository
import com.example.orchardhenbound.presentation.game.model.FallingItem
import com.example.orchardhenbound.presentation.game.model.ItemType
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

    // --- constants (rules) ---
    private val maxLives = 3
    private val goodItemProbability = 0.75f
    private val spawnIntervalMs = 650L

    private val baseW = 412f
    private val baseH = 892f
    private val playerYRatio = 699f / baseH
    private val initialPlayerXRatio = 34f / baseW

    private val _state = MutableStateFlow(GameUiState(lives = maxLives))
    val state: StateFlow<GameUiState> = _state.asStateFlow()

    private var loopJob: Job? = null

    // used for physics/spawn
    private var screenWpx: Float = 0f
    private var screenHpx: Float = 0f
    private var itemSizePx: Float = 0f
    private var playerWidthPx: Float = 0f
    private var playerHeightPx: Float = 0f

    private var spawnAccMs: Long = 0L

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

    fun stopLoop() {
        loopJob?.cancel()
        loopJob = null
    }

    fun pause() {
        _state.update { it.copy(isPaused = true) }
    }

    fun resume() {
        _state.update { it.copy(isPaused = false) }
    }

    fun onBackgrounded() {
        if (!_state.value.isGameOver) pause()
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
        val score = _state.value.score
        if (score > 0) {
            viewModelScope.launch { recordsRepository.addScore(score) }
        }

        _state.update { old ->
            val initX = (screenWpx * initialPlayerXRatio)
                .coerceIn(0f, max(0f, screenWpx - playerWidthPx))
            spawnAccMs = 0L
            old.copy(
                isPaused = false,
                isGameOver = false,
                lives = maxLives,
                score = 0,
                items = emptyList(),
                facingRight = true,
                playerX = initX
            )
        }
    }

    fun exitToMenuSaveScoreIfNeeded() {
        val score = _state.value.score
        if (score > 0) {
            viewModelScope.launch { recordsRepository.addScore(score) }
        }
    }

    private fun tick(dtSec: Float) {
        val dtMs = (dtSec * 1000f).toLong().coerceAtLeast(0L)

        spawnAccMs += dtMs
        if (spawnAccMs >= spawnIntervalMs) {
            spawnAccMs = 0L
            spawnItem()
        }

        moveItems(dtSec)
        resolveCollisions()
    }

    private fun spawnItem() {
        if (screenWpx <= 0f || itemSizePx <= 0f) return

        val type = if (Random.nextFloat() < goodItemProbability) {
            when (Random.nextInt(5)) {
                0 -> ItemType.GOOD_1
                1 -> ItemType.GOOD_2
                2 -> ItemType.GOOD_3
                3 -> ItemType.GOOD_4
                else -> ItemType.GOOD_5
            }
        } else {
            if (Random.nextBoolean()) ItemType.BAD_1 else ItemType.BAD_2
        }

        val x = Random.nextFloat() * max(0f, (screenWpx - itemSizePx))
        val speed = 250f + Random.nextFloat() * 220f

        val item = FallingItem(
            id = System.nanoTime(),
            type = type,
            xPx = x,
            yPx = -itemSizePx,
            speedPxPerSec = speed
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
        if (s.items.isEmpty()) return

        val playerY = screenHpx * playerYRatio

        val (collided, remaining) = s.items.partition { item ->
            overlaps(
                itemX = item.xPx,
                itemY = item.yPx,
                itemSize = itemSizePx,
                playerX = s.playerX,
                playerY = playerY,
                playerW = playerWidthPx,
                playerH = playerHeightPx
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
        val leftA = itemX
        val topA = itemY
        val rightA = itemX + itemSize
        val bottomA = itemY + itemSize

        val leftB = playerX
        val topB = playerY
        val rightB = playerX + playerW
        val bottomB = playerY + playerH

        return (rightA > leftB && leftA < rightB) && (bottomA > topB && topA < bottomB)
    }
}
