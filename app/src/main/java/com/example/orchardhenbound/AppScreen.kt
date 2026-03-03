<<<<<<< HEAD
package com.example.orchardhenbound
=======
package com.example.orchardhenbound.ui.navigation
>>>>>>> 9934ae4c0a8beaca4a779d9d471a4b4300bcf9a8

sealed class AppScreen(val route: String) {
    data object Loading : AppScreen("loading")
    data object Menu : AppScreen("menu")
    data object Game : AppScreen("game")
    data object Records : AppScreen("records")
    data object Privacy : AppScreen("privacy")
}
