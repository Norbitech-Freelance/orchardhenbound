package com.example.orchardhenbound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.orchardhenbound.presentation.game.GameScreen
import com.example.orchardhenbound.presentation.game.GameViewModel
import com.example.orchardhenbound.presentation.loading.LoadingScreen
import com.example.orchardhenbound.presentation.menu.MenuScreen
import com.example.orchardhenbound.presentation.privacy.PrivacyPolicyScreen
import com.example.orchardhenbound.presentation.records.RecordsScreen
import com.example.orchardhenbound.presentation.records.RecordsViewModel
import com.example.orchardhenbound.ui.theme.OrchardHenboundTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Fullscreen
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller.apply {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        setContent {
            OrchardHenboundTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf<AppScreen>(AppScreen.Loading) }

    when (currentScreen) {
        AppScreen.Loading -> LoadingScreen(
            onFinished = { currentScreen = AppScreen.Menu }
        )

        AppScreen.Menu -> MenuScreen(
            onStart = { currentScreen = AppScreen.Game },
            onRecords = { currentScreen = AppScreen.Records },
            onPrivacy = { currentScreen = AppScreen.PrivacyPolicy }
        )

        AppScreen.Game -> {
            val gameViewModel: GameViewModel = koinViewModel()
            GameScreen(
                viewModel = gameViewModel,
                onExitToMenu = { currentScreen = AppScreen.Menu }
            )
        }

        AppScreen.Records -> {
            val recordsViewModel: RecordsViewModel = koinViewModel()
            RecordsScreen(
                viewModel = recordsViewModel,
                onBack = { currentScreen = AppScreen.Menu }
            )
        }

        AppScreen.PrivacyPolicy -> PrivacyPolicyScreen(
            onBack = { currentScreen = AppScreen.Menu }
        )
    }
}
