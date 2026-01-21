package com.example.orchardhenbound.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.R

val BalooFontFamily = FontFamily(
    Font(resId = R.font.baloo_regular, weight = FontWeight.W400)
)

val Typography = Typography(
    // Use this in buttons (28sp by default)
    titleLarge = TextStyle(
        fontFamily = BalooFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 28.sp,
        lineHeight = 28.sp
    ),

    // Use this for big headers like "RECORDS" / "PRIVACY POLICY"
    headlineLarge = TextStyle(
        fontFamily = BalooFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 40.sp,
        lineHeight = 40.sp
    )
)
