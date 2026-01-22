package com.example.orchardhenbound.presentation.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.orchardhenbound.R
import com.example.orchardhenbound.presentation.components.FullScreenBackground
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(onFinished: () -> Unit) {
    // Animation frames for logo (loading_1 -> loading_2 -> loading_3)
    val frames = listOf(
        R.drawable.loading_1,
        R.drawable.loading_2,
        R.drawable.loading_3
    )

    val frameIndex = remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        val stepMs = 400L
        for (i in frames.indices) {
            frameIndex.intValue = i
            delay(stepMs)
        }
        onFinished()
    }

    // Base design dimensions from Figma
    val baseW = 412f
    val baseH = 917f

    // Logo dimensions and position
    val logoW = 154f
    val logoH = 25f
    val logoTop = 446f
    val logoLeft = 129f

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val density = LocalDensity.current

        val screenW = with(density) { maxWidth.toPx() }
        val screenH = with(density) { maxHeight.toPx() }

        // Adaptive scaling functions
        fun scaleX(v: Float) = with(density) { (screenW * (v / baseW)).toDp() }
        fun scaleY(v: Float) = with(density) { (screenH * (v / baseH)).toDp() }

        // Background
        FullScreenBackground(
            backgroundRes = R.drawable.bg_loading,
            contentDescription = stringResource(R.string.cd_loading_background)
        )

        // Animated logo (154×25 at position 129, 446)
        Image(
            painter = painterResource(frames[frameIndex.intValue]),
            contentDescription = stringResource(R.string.cd_loading_animation),
            modifier = Modifier
                .offset(x = scaleX(logoLeft), y = scaleY(logoTop))
                .size(width = scaleX(logoW), height = scaleY(logoH)),
            contentScale = ContentScale.Fit
        )
    }
}