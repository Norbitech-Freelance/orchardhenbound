package com.example.orchardhenbound.presentation.game

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
import androidx.compose.runtime.DisposableEffect
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
import androidx.compose.ui.unit.dp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.presentation.components.FullScreenBackground
import com.example.orchardhenbound.presentation.game.components.GameOverOverlay
import com.example.orchardhenbound.presentation.game.components.HeartsRow
import com.example.orchardhenbound.presentation.game.components.PauseOverlay
import com.example.orchardhenbound.presentation.game.components.ScorePlate
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

    // start loop once while screen alive
    DisposableEffect(Unit) {
        viewModel.startLoop()
        onDispose { /* optional: viewModel.stopLoop() */ }
    }

    // Back handler like before
    BackHandler(enabled = !state.isGameOver) {
        if (!state.isPaused) {
            viewModel.pause()
        } else {
            viewModel.exitToMenuSaveScoreIfNeeded()
            onExitToMenu()
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val density = LocalDensity.current

        val wPx = with(density) { maxWidth.toPx() }
        val hPx = with(density) { maxHeight.toPx() }

        val itemSizeDp = 46.dp
        val itemSizePx = with(density) { itemSizeDp.toPx() }

        val playerWidthDp = 69.dp
        val playerHeightDp = 100.dp
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

        // Background
        FullScreenBackground(
            backgroundRes = backgroundRes,
            contentDescription = stringResource(R.string.cd_game_background)
        )

        // Falling items
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

        // Pause button
        Image(
            painter = painterResource(id = R.drawable.btn_pause),
            contentDescription = stringResource(R.string.cd_pause_button),
            modifier = Modifier
                .padding(start = 18.dp, top = 52.dp)
                .size(width = 57.dp, height = 60.dp)
                .clickableNoRipple {
                    if (!state.isGameOver && !state.isPaused) {
                        viewModel.pause()
                    }
                },
            contentScale = ContentScale.Fit
        )

        // Lives
        HeartsRow(
            lives = state.lives,
            maxLives = MAX_LIVES,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 18.dp, top = 58.dp)
        )


        // Player
        Box(
            modifier = Modifier
                .size(playerWidthDp, playerHeightDp)
                .offsetPx(state.playerX, hPx * (699f / 892f))
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
                    id = if (state.facingRight) R.drawable.player_right else R.drawable.player_left
                ),
                contentDescription = stringResource(R.string.cd_player_character),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }

        // Overlays
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
