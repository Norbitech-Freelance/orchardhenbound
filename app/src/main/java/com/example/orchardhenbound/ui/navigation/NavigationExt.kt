package com.example.orchardhenbound.ui.navigation

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController

fun NavHostController.navigateSingle(route: String) {
    if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        navigate(route) {
            launchSingleTop = true
        }
    }
}

fun NavHostController.safePopBackStack() {
    if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        popBackStack()
    }
}