package com.example.orchardhenbound.ui.presentation.game.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.ui.presentation.components.CustomButton
import com.example.orchardhenbound.ui.presentation.components.FullScreenBackground

@Composable
fun PauseOverlay(
    onResume: () -> Unit,
    onExit: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        FullScreenBackground(
            backgroundRes = R.drawable.bg_pause,
            contentDescription = null
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.weight(0.35f))

            Image(
                painter = painterResource(id = R.drawable.pause),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.67f)
                    .aspectRatio(2.93f),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.weight(0.06f))

            CustomButton(
                text = stringResource(R.string.game_resume),
                onClick = onResume
            )
            CustomButton(
                text = stringResource(R.string.game_exit),
                onClick = onExit
            )
            Spacer(modifier = Modifier.weight(0.45f))
        }
    }
}
