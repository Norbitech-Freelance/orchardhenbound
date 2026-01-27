package com.example.orchardhenbound.presentation.game.components

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.presentation.components.FullScreenBackground
import com.example.orchardhenbound.utils.extensions.clickableNoRipple
import java.util.Locale

@Composable
fun PauseOverlay(
    onResume: () -> Unit,
    onExit: () -> Unit
) {
    // Обработка системной кнопки "Назад" - снимает паузу
    BackHandler(onBack = onResume)

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val baseW = 412f
        val baseH = 917f

        val pausePlateWidth = maxWidth * (278.71f / baseW)
        val pausePlateHeight = maxHeight * (95f / baseH)
        val pausePlateLeft = maxWidth * (66f / baseW)
        val pausePlateTop = maxHeight * (200f / baseH)

        val buttonWidth = maxWidth * (260f / baseW)
        val buttonHeight = maxHeight * (80f / baseH)

        FullScreenBackground(
            backgroundRes = R.drawable.bg_overlay,
            contentDescription = null
        )

        Image(
            painter = painterResource(id = R.drawable.pause),
            contentDescription = "Pause",
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = pausePlateLeft, y = pausePlateTop)
                .size(width = pausePlateWidth, height = pausePlateHeight),
            contentScale = ContentScale.Fit
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PauseButton(
                text = stringResource(R.string.game_resume),
                onClick = onResume,
                width = buttonWidth,
                height = buttonHeight
            )
            PauseButton(
                text = stringResource(R.string.game_exit),
                onClick = onExit,
                width = buttonWidth,
                height = buttonHeight
            )
        }
    }
}

@Composable
private fun PauseButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes backgroundRes: Int = R.drawable.btn_primary_bg,
    width: Dp = 260.dp,
    height: Dp = 80.dp
) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFFF9E7),
            Color(0xFFFFEBBE)
        )
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
            .clickableNoRipple(onClick = onClick), // ИСПРАВЛЕНО: добавлен именованный параметр
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
