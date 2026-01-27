package com.example.orchardhenbound.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.utils.extensions.clickableNoRipple

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes backgroundRes: Int = R.drawable.btn_back
) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.138f)
            .height(60.dp)
            .clickableNoRipple(onClick = onClick), // ИСПРАВЛЕНО: добавлен именованный параметр
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = backgroundRes),
            contentDescription = "Back button",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}
