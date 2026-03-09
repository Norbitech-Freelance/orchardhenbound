package com.example.orchardhenbound.ui.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.R

@Composable
fun ScorePlate(
    score: Int,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 32.sp,
    @DrawableRes backgroundRes: Int = R.drawable.bg_score_plate
) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.3f)
            .aspectRatio(3f),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = backgroundRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
        WhiteOutlinedText(
            text = score.toString(),
            maxFontSize = fontSize
        )
    }
}