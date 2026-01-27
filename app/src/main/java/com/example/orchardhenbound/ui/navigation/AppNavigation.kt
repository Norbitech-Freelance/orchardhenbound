package com.example.orchardhenbound.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.orchardhenbound.presentation.game.GameScreen
import com.example.orchardhenbound.presentation.game.GameViewModel
import com.example.orchardhenbound.presentation.loading.LoadingScreen
import com.example.orchardhenbound.presentation.menu.MenuScreen
import com.example.orchardhenbound.presentation.privacy.PrivacyPolicyScreen
import com.example.orchardhenbound.presentation.records.RecordsScreen
import com.example.orchardhenbound.presentation.records.RecordsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreen.Loading.route
    ) {
        composable(AppScreen.Loading.route) {
            LoadingScreen(
                onFinished = {
                    navController.navigate(AppScreen.Menu.route) {
                        popUpTo(AppScreen.Loading.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(AppScreen.Menu.route) {
            MenuScreen(
                onStart = {
                    navController.navigate(AppScreen.Game.route) {
                        launchSingleTop = true
                    }
                },
                onRecords = {
                    navController.navigate(AppScreen.Records.route) {
                        launchSingleTop = true
                    }
                },
                onPrivacy = {
                    navController.navigate(AppScreen.Privacy.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(AppScreen.Game.route) {
            val gameViewModel: GameViewModel = koinViewModel()
            GameScreen(
                viewModel = gameViewModel,
                onExitToMenu = {
                    navController.navigate(AppScreen.Menu.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(AppScreen.Records.route) {
            val recordsViewModel: RecordsViewModel = koinViewModel()
            RecordsScreen(
                viewModel = recordsViewModel,
                onBack = {
                    if (navController.currentBackStackEntry != null) {
                        navController.popBackStack()
                    }
                }
            )
        }

        composable(AppScreen.Privacy.route) {
            PrivacyPolicyScreen(
                onBack = {
                    if (navController.currentBackStackEntry != null) {
                        navController.popBackStack()
                    }
                }
            )
        }
    }
}
