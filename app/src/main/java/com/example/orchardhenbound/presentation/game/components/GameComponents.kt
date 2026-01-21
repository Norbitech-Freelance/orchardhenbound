package com.example.orchardhenbound.presentation.game.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.presentation.components.CustomButton
import com.example.orchardhenbound.presentation.components.FullScreenBackground
import com.example.orchardhenbound.utils.clickableNoRipple
import kotlin.math.min

@Composable
fun PauseOverlay(
    onResume: () -> Unit,
    onExit: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        FullScreenBackground(
            backgroundRes = R.drawable.bg_overlay,
            contentDescription = null
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomButton(text = stringResource(R.string.game_resume), onClick = onResume)
            CustomButton(text = stringResource(R.string.game_exit), onClick = onExit)
        }
    }
}

@Composable
fun GameOverOverlay(
    score: Int,
    onPlayAgain: () -> Unit,
    onExit: () -> Unit
) {
    val baseW = 412f
    val baseH = 892f
    val gameOverW = 280.19f
    val gameOverH = 214f
    val ratio = gameOverW / gameOverH

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val density = LocalDensity.current

        // важно: maxWidth/maxHeight чтобы scope считался использованным
        val w = with(density) { maxWidth.toPx() }
        val h = with(density) { maxHeight.toPx() }

        fun x(v: Float): Dp = (w * (v / baseW)).dp
        fun y(v: Float): Dp = (h * (v / baseH)).dp

        FullScreenBackground(
            backgroundRes = R.drawable.bg_overlay,
            contentDescription = null
        )

        Image(
            painter = painterResource(id = R.drawable.img_game_over),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .absoluteOffset(y = y(210f))
                .width(x(gameOverW))
                .aspectRatio(ratio),
            contentScale = ContentScale.Fit
        )

        val s = min(w / baseW, h / baseH)

        ScorePlate(
            score = score,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .absoluteOffset(y = y(450f)),
            width = x(120f),
            height = y(40f),
            fontSize = (32f * s).sp,
            strokeWidthPx = 3.8f * s
        )

        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .absoluteOffset(y = y(556f)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(x(8f))
        ) {
            Image(
                painter = painterResource(id = R.drawable.btn_game_over_back),
                contentDescription = null,
                modifier = Modifier
                    .size(width = x(57f), height = y(60f))
                    .clickableNoRipple { onExit() },
                contentScale = ContentScale.Fit
            )

            CustomButton(
                text = stringResource(R.string.game_play_again),
                onClick = onPlayAgain,
                backgroundRes = R.drawable.btn_play_again_bg,
                width = x(220f),
                height = y(60f),
                fillBrush = Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.primary
                    )
                )
            )
        }
    }
}
