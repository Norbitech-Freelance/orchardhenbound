package com.example.orchardhenbound.presentation.game.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.presentation.components.BackButton
import com.example.orchardhenbound.presentation.components.FullScreenBackground
import com.example.orchardhenbound.ui.theme.PLATE_BOTTOM
import com.example.orchardhenbound.ui.theme.PLATE_TOP
import com.example.orchardhenbound.utils.extensions.clickableNoRipple
import java.util.Locale

@Composable
fun GameOverOverlay(
    score: Int,
    onPlayAgain: () -> Unit,
    onExit: () -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val baseW = 412f
        val baseH = 917f

        val gameOverPlateWidth = maxWidth * (280.19f / baseW)
        val gameOverPlateHeight = maxHeight * (214f / baseH)
        val gameOverPlateLeft = maxWidth * (66f / baseW)
        val gameOverPlateTop = maxHeight * (160f / baseH)

        val scorePlateWidth = maxWidth * (120f / baseW)
        val scorePlateHeight = maxHeight * (40f / baseH)
        val scorePlateLeft = maxWidth * (150f / baseW)
        val scorePlateTop = maxHeight * (420f / baseH)

        val buttonsTop = maxHeight * (556f / baseH)
        val buttonsLeft = maxWidth * (63.5f / baseW)
        val buttonGap = maxWidth * (8f / baseW)

        val backButtonWidth = maxWidth * (57f / baseW)
        val backButtonHeight = maxHeight * (60f / baseH)

        val playAgainWidth = maxWidth * (220f / baseW)
        val playAgainHeight = maxHeight * (60f / baseH)
        val playAgainLeft = buttonsLeft + backButtonWidth + buttonGap

        FullScreenBackground(
            backgroundRes = R.drawable.bg_overlay,
            contentDescription = null
        )

        Image(
            painter = painterResource(id = R.drawable.game_over),
            contentDescription = "Game Over",
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = gameOverPlateLeft, y = gameOverPlateTop)
                .size(width = gameOverPlateWidth, height = gameOverPlateHeight),
            contentScale = ContentScale.Fit
        )

        ScorePlate(
            score = score,
            width = scorePlateWidth,
            height = scorePlateHeight,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = scorePlateLeft, y = scorePlateTop)
        )

        BackButton(
            onClick = onExit,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = buttonsLeft, y = buttonsTop)
                .width(backButtonWidth)
                .height(backButtonHeight)
        )

        GameOverButton(
            text = "PLAY AGAIN",
            onClick = onPlayAgain,
            width = playAgainWidth,
            height = playAgainHeight,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = playAgainLeft, y = buttonsTop)
        )
    }
}

@Composable
private fun GameOverButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes backgroundRes: Int = R.drawable.btn_primary_bg,
    width: Dp = 220.dp,
    height: Dp = 60.dp
) {
    val gradient = Brush.verticalGradient(
        colors = listOf(PLATE_TOP, PLATE_BOTTOM)
    )

    val textStyle = MaterialTheme.typography.titleLarge.copy(
        fontSize = 28.sp,
        fontWeight = FontWeight.W400,
        brush = gradient
    )

    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .clickableNoRipple(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = backgroundRes),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = text.uppercase(Locale.getDefault()),
            style = textStyle
        )
    }
}
