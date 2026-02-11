package com.example.orchardhenbound.presentation.privacy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.orchardhenbound.R
import com.example.orchardhenbound.presentation.components.FullScreenBackground
import com.example.orchardhenbound.presentation.game.components.TopBarRow

@Composable
fun PrivacyPolicyScreen(onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        FullScreenBackground(
            backgroundRes = R.drawable.bg_privacy,
            contentDescription = stringResource(R.string.cd_privacy_background)
        )

        TopBarRow(
            buttonText = stringResource(R.string.privacy_policy_title),
            onClick = onBack
        )
    }
}
