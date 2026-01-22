package com.example.orchardhenbound.presentation.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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

        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            CustomButton(
                text = stringResource(R.string.menu_start),
                onClick = onStart
            )
            CustomButton(
                text = stringResource(R.string.menu_records),
                onClick = onRecords
            )
            CustomButton(
                text = stringResource(R.string.menu_privacy_policy),
                onClick = onPrivacy
            )
        }
    }
}
