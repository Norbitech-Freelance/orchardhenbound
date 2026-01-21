package com.example.orchardhenbound.presentation.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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

    // базовые размеры макета
    val baseW = 412f
    val baseH = 892f
    val loadingW = 152f
    val loadingH = 24f
    val ratio = loadingW / loadingH

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val density = LocalDensity.current

        val screenW = with(density) { maxWidth.toPx() }
        val screenH = with(density) { maxHeight.toPx() }

        fun x(v: Float) = (screenW * (v / baseW)).dp

        FullScreenBackground(
            backgroundRes = R.drawable.bg_loading,
            contentDescription = stringResource(R.string.cd_loading_background)
        )

        Image(
            painter = painterResource(frames[frameIndex.intValue]),
            contentDescription = stringResource(R.string.cd_loading_animation),
            modifier = Modifier
                .align(Alignment.Center)
                .width(x(loadingW))
                .aspectRatio(ratio),
            contentScale = ContentScale.Fit
        )
    }
}
