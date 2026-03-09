package com.example.orchardhenbound.ui.presentation.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.orchardhenbound.R
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(onFinished: () -> Unit) {

    val frames = listOf(
        R.drawable.loading_1,
        R.drawable.loading_2,
        R.drawable.loading_3,
        R.drawable.loading_4
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

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.bg_loading),
            contentDescription = stringResource(R.string.cd_loading_background),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.25f))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.67f)
                    .aspectRatio(1.35f),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.weight(0.15f))

            Image(
                painter = painterResource(id = frames[frameIndex.intValue]),
                contentDescription = stringResource(R.string.cd_loading_animation),
                modifier = Modifier
                    .fillMaxWidth(0.37f)
                    .aspectRatio(6.16f),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_7_pro")
@Composable
fun LoadingScreenPreview() {
    MaterialTheme {
        LoadingScreen(onFinished = {})
    }
}