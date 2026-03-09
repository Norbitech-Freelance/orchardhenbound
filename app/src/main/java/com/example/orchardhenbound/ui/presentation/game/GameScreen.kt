@file:Suppress("COMPOSE_APPLIER_CALL_MISMATCH")

package com.example.orchardhenbound.ui.presentation.game

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.orchardhenbound.R
import com.example.orchardhenbound.ui.presentation.components.FullScreenBackground
import com.example.orchardhenbound.ui.presentation.game.dialog.GameOverOverlay
import com.example.orchardhenbound.ui.presentation.game.dialog.HeartsRow
import com.example.orchardhenbound.ui.presentation.game.dialog.PauseOverlay
import com.example.orchardhenbound.ui.presentation.components.ScorePlate
import com.example.orchardhenbound.utils.getDrawableRes
import com.example.orchardhenbound.utils.extensions.clickableNoRipple
import com.example.orchardhenbound.utils.extensions.offsetPx

private const val MAX_LIVES = 3

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    onExitToMenu: () -> Unit,
    @DrawableRes backgroundRes: Int = R.drawable.bg_game
) {
    val state by viewModel.state.collectAsState()

    LifecycleEventEffect(Lifecycle.Event.ON_PAUSE) {
        if (!state.isPaused && !state.isGameOver) {
            viewModel.pause()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.startLoop()
    }

    BackHandler(enabled = !state.isGameOver) {
        if (!state.isPaused) {
            viewModel.pause()
        } else {
            viewModel.resume()
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val density = LocalDensity.current

        val screenWidthDp = maxWidth
        val screenHeightDp = maxHeight

        val wPx = with(density) { screenWidthDp.toPx() }
        val hPx = with(density) { screenHeightDp.toPx() }

        val itemSizeDp = screenWidthDp * 0.11f
        val playerWidthDp = screenWidthDp * 0.17f
        val playerHeightDp = screenHeightDp * 0.11f
        val pauseButtonSize = screenWidthDp * 0.14f
        val horizontalPadding = screenWidthDp * 0.04f
        val topBarPadding = screenHeightDp * 0.06f
        val scoreTopPadding = screenHeightDp * 0.11f
        val playerYOffsetRatio = 0.78f

        val itemSizePx = with(density) { itemSizeDp.toPx() }
        val playerWidthPx = with(density) { playerWidthDp.toPx() }
        val playerHeightPx = with(density) { playerHeightDp.toPx() }

        LaunchedEffect(wPx, hPx, itemSizePx, playerWidthPx, playerHeightPx) {
            viewModel.onScreenMetrics(
                wPx = wPx,
                hPx = hPx,
                itemSizePx = itemSizePx,
                playerWidthPx = playerWidthPx,
                playerHeightPx = playerHeightPx
            )
        }

        FullScreenBackground(
            backgroundRes = backgroundRes,
            contentDescription = stringResource(R.string.cd_game_background)
        )

        state.items.forEach { item ->
            Image(
                painter = painterResource(id = item.type.getDrawableRes()),
                contentDescription = null,
                modifier = Modifier
                    .size(itemSizeDp)
                    .offsetPx(item.xPx, item.yPx),
                contentScale = ContentScale.Fit
            )
        }

        Image(
            painter = painterResource(id = R.drawable.btn_pause),
            contentDescription = stringResource(R.string.cd_pause_button),
            modifier = Modifier
                .padding(start = horizontalPadding, top = topBarPadding)
                .size(pauseButtonSize)
                .clickableNoRipple {
                    if (!state.isGameOver && !state.isPaused) {
                        viewModel.pause()
                    }
                },
            contentScale = ContentScale.Fit
        )

        HeartsRow(
            lives = state.lives,
            maxLives = MAX_LIVES,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = horizontalPadding, top = topBarPadding)
        )

        ScorePlate(
            score = state.score,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = scoreTopPadding)
        )

        Box(
            modifier = Modifier
                .size(playerWidthDp, playerHeightDp)
                .offsetPx(state.playerX, hPx * playerYOffsetRatio)
                .pointerInput(state.isPaused, state.isGameOver) {
                    if (state.isPaused || state.isGameOver) return@pointerInput
                    detectDragGestures { _, dragAmount ->
                        viewModel.onDrag(dragAmount.x)
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    id = if (state.facingRight) R.drawable.player else R.drawable.player
                ),
                contentDescription = stringResource(R.string.cd_player_character),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }

        if (state.isPaused && !state.isGameOver) {
            PauseOverlay(
                onResume = { viewModel.resume() },
                onExit = {
                    viewModel.exitToMenuSaveScoreIfNeeded()
                    onExitToMenu()
                }
            )
        }

        if (state.isGameOver) {
            GameOverOverlay(
                score = state.score,
                onPlayAgain = { viewModel.playAgain() },
                onExit = {
                    viewModel.exitToMenuSaveScoreIfNeeded()
                    onExitToMenu()
                }
            )
        }
    }
}