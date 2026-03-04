package com.example.orchardhenbound.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.orchardhenbound.ui.presentation.game.GameScreen
import com.example.orchardhenbound.ui.presentation.game.GameViewModel
import com.example.orchardhenbound.ui.presentation.loading.LoadingScreen
import com.example.orchardhenbound.ui.presentation.menu.MenuScreen
import com.example.orchardhenbound.ui.presentation.privacy.PrivacyPolicyScreen
import com.example.orchardhenbound.ui.presentation.records.RecordsScreen
import com.example.orchardhenbound.ui.presentation.records.RecordsViewModel
import org.koin.androidx.compose.koinViewModel

object Routes {
    const val LOADING = "loading"
    const val MENU = "menu"
    const val GAME = "game"
    const val RECORDS = "records"
    const val PRIVACY = "privacy"
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
                    if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate(Routes.MENU) {
                            popUpTo(Routes.LOADING) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                }
            )
        }

        composable(Routes.MENU) {
            MenuScreen(
                onStart = { navController.navigateSingle(Routes.GAME) },
                onRecords = { navController.navigateSingle(Routes.RECORDS) },
                onPrivacy = { navController.navigateSingle(Routes.PRIVACY) }
            )
        }

        composable(Routes.GAME) {
            val viewModel: GameViewModel = koinViewModel()

            GameScreen(
                viewModel = viewModel,
                onExitToMenu = {
                    navController.safePopBackStack()
                }
            )
        }

        composable(Routes.RECORDS) {
            val viewModel: RecordsViewModel = koinViewModel()

            RecordsScreen(
                viewModel = viewModel,
                onBack = { navController.safePopBackStack() }
            )
        }

        composable(Routes.PRIVACY) {
            PrivacyPolicyScreen(
                onBack = { navController.safePopBackStack() }
            )
        }
    }
}