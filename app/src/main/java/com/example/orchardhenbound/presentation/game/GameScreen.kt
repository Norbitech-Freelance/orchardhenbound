package com.example.orchardhenbound.presentation.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.orchardhenbound.R
import com.example.orchardhenbound.presentation.components.FullScreenBackground
import com.example.orchardhenbound.presentation.game.components.GameContent
import com.example.orchardhenbound.presentation.game.components.GameOverOverlay
import com.example.orchardhenbound.presentation.game.components.PauseOverlay

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    onExitToMenu: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        FullScreenBackground(
            backgroundRes = R.drawable.bg_game,
            contentDescription = "Game background"
        )


        GameContent(
            viewModel = viewModel,
            onPause = { viewModel.pause() }
        )


        if (state.isPaused) {
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
