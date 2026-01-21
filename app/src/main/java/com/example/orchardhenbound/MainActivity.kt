package com.example.orchardhenbound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.orchardhenbound.presentation.game.GameScreen
import com.example.orchardhenbound.presentation.game.GameViewModel
import com.example.orchardhenbound.presentation.loading.LoadingScreen
import com.example.orchardhenbound.presentation.menu.MenuScreen
import com.example.orchardhenbound.presentation.navigation.Routes
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
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOADING
    ) {
        composable(Routes.LOADING) {
            LoadingScreen(
                onFinished = {

                    navController.navigate(Routes.MENU) {
                        popUpTo(Routes.LOADING) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.MENU) {
            MenuScreen(
                onStart = { navController.navigate(Routes.GAME) },
                onRecords = { navController.navigate(Routes.RECORDS) },
                onPrivacy = { navController.navigate(Routes.PRIVACY) }
            )
        }

        composable(Routes.GAME) {
            val gameViewModel: GameViewModel = koinViewModel()
            GameScreen(
                viewModel = gameViewModel,
                onExitToMenu = {
                    navController.navigate(Routes.MENU) {

                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(Routes.RECORDS) {
            val recordsViewModel: RecordsViewModel = koinViewModel()
            RecordsScreen(
                viewModel = recordsViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PRIVACY) {
            PrivacyPolicyScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}