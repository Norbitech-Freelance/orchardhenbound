package com.example.orchardhenbound.presentation.privacy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.presentation.components.BackButton
import com.example.orchardhenbound.presentation.components.FullScreenBackground
import com.example.orchardhenbound.presentation.components.TitleOutlinedText

@Composable
fun PrivacyPolicyScreen(onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        FullScreenBackground(
            backgroundRes = R.drawable.bg_privacy,
            contentDescription = stringResource(R.string.cd_privacy_background)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 60.dp),
                //.height(60.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(onClick = onBack)

            TitleOutlinedText(
                text = stringResource(R.string.privacy_policy_title),
                maxFontSize = 40.sp
            )
        }
    }
}
