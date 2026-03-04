package com.example.orchardhenbound.ui.presentation.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.orchardhenbound.R
import com.example.orchardhenbound.ui.presentation.components.CustomButton
import com.example.orchardhenbound.ui.presentation.components.FullScreenBackground

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
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.8f))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.67f)
                    .aspectRatio(1.35f),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.weight(0.8f))

            CustomButton(
                text = stringResource(R.string.menu_start),
                onClick = onStart
            )

            Spacer(modifier = Modifier.weight(0.1f))

            CustomButton(
                text = stringResource(R.string.menu_records),
                onClick = onRecords
            )

            Spacer(modifier = Modifier.weight(0.1f))

            CustomButton(
                text = stringResource(R.string.menu_privacy_policy),
                onClick = onPrivacy
            )

            Spacer(modifier = Modifier.weight(1.8f))
        }
    }
}
@Preview(showBackground = true, device = "id:pixel_7_pro")
@Composable
fun MenuScreenPreview() {
    MaterialTheme {
        MenuScreen(
            onStart = {},
            onRecords = {},
            onPrivacy = {}
        )
    }
}
