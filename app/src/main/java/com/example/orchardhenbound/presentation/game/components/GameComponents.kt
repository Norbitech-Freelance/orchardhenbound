package com.example.orchardhenbound.presentation.game.components

import androidx.annotation.DrawableRes
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.presentation.components.CustomButton
import com.example.orchardhenbound.presentation.components.FullScreenBackground
import com.example.orchardhenbound.utils.clickableNoRipple
import kotlin.math.min

/* ---------------- HEARTS ---------------- */

@Composable
fun HeartsRow(
    lives: Int,
    maxLives: Int = 3,
    heartSize: Dp = 28.dp,
    gap: Dp = 6.dp,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(gap),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(maxLives) { i ->
            val resId =
                if (i < lives) R.drawable.ic_heart_full else R.drawable.ic_heart_empty

            Image(
                painter = painterResource(id = resId),
                contentDescription = null,
                modifier = Modifier.size(heartSize),
                contentScale = ContentScale.Fit
            )
        }
    }
}

/* ---------------- SCORE PLATE ---------------- */

@Composable
fun ScorePlate(
    score: Int,
    modifier: Modifier = Modifier,
    width: Dp = 120.dp,
    height: Dp = 40.dp,
    fontSize: TextUnit = 32.sp,
    strokeWidthPx: Float = 3.8f,
    @DrawableRes backgroundRes: Int = R.drawable.bg_score_plate
) {
    val gradient = Brush.verticalGradient(
        listOf(
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.primary
        )
    )

    val baseStyle = TextStyle(
        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
        fontSize = fontSize,
        lineHeight = fontSize,
        fontWeight = FontWeight.W400,
        textAlign = TextAlign.Center
    )

    Box(
        modifier = modifier.size(width, height),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = backgroundRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )

        Text(
            text = score.toString(),
            style = baseStyle.copy(
                color = MaterialTheme.colorScheme.primary,
                drawStyle = Stroke(width = strokeWidthPx, join = StrokeJoin.Round)
            )
        )

        Text(
            text = score.toString(),
            style = baseStyle.copy(brush = gradient)
        )
    }
}

/* ---------------- PAUSE OVERLAY ---------------- */

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

/* ---------------- GAME OVER ---------------- */

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
        val w = constraints.maxWidth.toFloat()
        val h = constraints.maxHeight.toFloat()

        fun x(v: Float) = (w * (v / baseW)).dp
        fun y(v: Float) = (h * (v / baseH)).dp

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
