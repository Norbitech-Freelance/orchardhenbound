package com.example.orchardhenbound

sealed class AppScreen {
    data object Loading : AppScreen()
    data object Menu : AppScreen()
    data object Game : AppScreen()
    data object Records : AppScreen()
    data object PrivacyPolicy : AppScreen()
}
