package com.example.orchardhenbound.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.utils.clickableNoRipple

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    @DrawableRes icon: Int = R.drawable.btn_back
) {
    Image(
        painter = painterResource(id = icon),
        contentDescription = stringResource(R.string.cd_back_button),
        modifier = modifier
            .size(size)
            .clickableNoRipple(onClick),
        contentScale = ContentScale.Fit
    )
}
