package com.example.orchardhenbound.ui.navigation

sealed class AppScreen(val route: String) {
    data object Loading : AppScreen("loading")
    data object Menu : AppScreen("menu")
    data object Game : AppScreen("game")
    data object Records : AppScreen("records")
    data object Privacy : AppScreen("privacy")
}
