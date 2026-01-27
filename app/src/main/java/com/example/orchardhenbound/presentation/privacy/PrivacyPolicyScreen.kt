package com.example.orchardhenbound.presentation.privacy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.presentation.components.BackButton
import com.example.orchardhenbound.presentation.components.FullScreenBackground
import com.example.orchardhenbound.presentation.components.StrokeText
import com.example.orchardhenbound.ui.theme.BalooFontFamily
import com.example.orchardhenbound.ui.theme.PLATE_BOTTOM
import com.example.orchardhenbound.ui.theme.PLATE_TOP
import com.example.orchardhenbound.ui.theme.STROKE_PRIMARY

@Composable
fun PrivacyPolicyScreen(onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        FullScreenBackground(
            backgroundRes = R.drawable.bg_privacy,
            contentDescription = stringResource(R.string.cd_privacy_background)
        )

        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val baseW = 412f
            val baseH = 917f

            val topOffset = maxHeight * (60f / baseH)
            val leftOffset = maxWidth * (24f / baseW)
            val rightOffset = maxWidth * (24f / baseW)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = topOffset)
                    .padding(start = leftOffset, end = rightOffset),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BackButton(onClick = onBack)

                StrokeText(
                    text = stringResource(R.string.privacy_policy_title),
                    fontFamily = BalooFontFamily,
                    fontSize = 40.sp,
                    fillBrush = Brush.linearGradient(listOf(PLATE_TOP, PLATE_BOTTOM)),
                    strokeColor = STROKE_PRIMARY,
                    strokeWidth = 1f
                )
            }
        }
    }
}
