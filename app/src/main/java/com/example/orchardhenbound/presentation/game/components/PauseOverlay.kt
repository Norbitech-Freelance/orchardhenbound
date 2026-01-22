package com.example.orchardhenbound.presentation.game.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.presentation.components.CustomButton
import com.example.orchardhenbound.presentation.components.FullScreenBackground

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
            CustomButton(
                text = stringResource(R.string.game_resume),
                onClick = onResume
            )
            CustomButton(
                text = stringResource(R.string.game_exit),
                onClick = onExit
            )
        }
    }
}
