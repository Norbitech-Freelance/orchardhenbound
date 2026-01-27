package com.example.orchardhenbound.presentation.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.presentation.components.CustomButton
import com.example.orchardhenbound.presentation.components.FullScreenBackground

@Composable
fun MenuScreen(
    onStart: () -> Unit,
    onRecords: () -> Unit,
    onPrivacy: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        FullScreenBackground(
            backgroundRes = R.drawable.bg_menu,
            contentDescription = stringResource(R.string.cd_menu_background)
        )

        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val baseH = 917f
            val buttonsTop = maxHeight * (450f / baseH)

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(buttonsTop))

                CustomButton(
                    text = stringResource(R.string.menu_start),
                    onClick = onStart,
                    modifier = Modifier.fillMaxWidth(0.63f),
                    height = 80.dp
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomButton(
                    text = stringResource(R.string.menu_records),
                    onClick = onRecords,
                    modifier = Modifier.fillMaxWidth(0.63f),
                    height = 80.dp
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomButton(
                    text = stringResource(R.string.menu_privacy_policy),
                    onClick = onPrivacy,
                    modifier = Modifier.fillMaxWidth(0.63f),
                    height = 80.dp
                )
            }
        }
    }
}
