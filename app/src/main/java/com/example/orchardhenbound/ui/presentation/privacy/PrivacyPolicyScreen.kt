package com.example.orchardhenbound.ui.presentation.privacy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.ui.presentation.components.BackButton
import com.example.orchardhenbound.ui.presentation.components.FullScreenBackground
import com.example.orchardhenbound.ui.presentation.components.TitleOutlinedText

@Composable
fun PrivacyPolicyScreen(onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        FullScreenBackground(
            backgroundRes = R.drawable.bg_menu,
            contentDescription = stringResource(R.string.cd_privacy_background)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(onClick = onBack)

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                TitleOutlinedText(
                    text = stringResource(R.string.privacy_policy_title),
                    maxFontSize = 40.sp
                )
            }
        }

    }
}

@Preview(showBackground = true, device = "id:pixel_7_pro")
@Composable
fun PrivacyPolicyScreenPreview() {
    MaterialTheme {
        PrivacyPolicyScreen(
            onBack = {}
        )
    }
}