package com.example.orchardhenbound.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

fun NavController.navigateSingleTop(
    route: String,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(route) {
        launchSingleTop = true
        builder()
    }
}
