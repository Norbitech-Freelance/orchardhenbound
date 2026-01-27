package com.example.orchardhenbound.presentation.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.orchardhenbound.R
import com.example.orchardhenbound.presentation.components.FullScreenBackground
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(onFinished: () -> Unit) {
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

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val baseW = 412f
        val baseH = 917f

        val logoWidth = maxWidth * (154f / baseW)
        val logoHeight = maxHeight * (25f / baseH)
        val logoLeft = maxWidth * (129f / baseW)
        val logoTop = maxHeight * (446f / baseH)

        FullScreenBackground(
            backgroundRes = R.drawable.bg_loading,
            contentDescription = stringResource(R.string.cd_loading_background)
        )

        Image(
            painter = painterResource(frames[frameIndex.intValue]),
            contentDescription = stringResource(R.string.cd_loading_animation),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = logoLeft, y = logoTop)
                .size(width = logoWidth, height = logoHeight),
            contentScale = ContentScale.Fit
        )
    }
}
