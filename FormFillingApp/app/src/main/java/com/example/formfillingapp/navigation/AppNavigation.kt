package com.example.formfillingapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.formfillingapp.mvi.intent.WelcomeIntent
import com.example.formfillingapp.mvi.viewmodel.LoginViewModel
import com.example.formfillingapp.mvi.viewmodel.WelcomeViewModel
import com.example.formfillingapp.ui.login.LoginScreen
import com.example.formfillingapp.ui.welcome.WelcomeScreen

/**
 * Main navigation component for the app
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Navigation controller

    NavHost(
        navController = navController,
        startDestination = NavRoutes.LOGIN
    ) {
        // Login screen
        composable(
            route = NavRoutes.LOGIN,
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() }
        ) {
            val viewModel: LoginViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            // Handle navigation effect
            LaunchedEffect(key1 = true) {
                viewModel.effect.collect { effect ->
                    when (effect) {
                        is com.example.formfillingapp.mvi.model.LoginEffect.NavigateToWelcome -> {
                            navController.navigate(NavRoutes.createWelcomeRoute(effect.username)) {
                                popUpTo(NavRoutes.LOGIN) { inclusive = false }
                            }
                        }

                        else -> { /* Ignore other effects */
                        }
                    }
                }
            }

            LoginScreen(
                state = state,
                onIntent = viewModel::processIntent
            )
        }

        // Welcome screen
        composable(
            route = NavRoutes.WELCOME,
            arguments = listOf(
                navArgument("username") {
                    type = NavType.StringType
                }
            ),
            enterTransition = { slideInFromLeft() },
            exitTransition = { slideOutToRight() }
        ) { backStackEntry ->
            val viewModel: WelcomeViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            // Get username from navigation arguments
            val username = backStackEntry.arguments?.getString("username") ?: ""

            // Set username in ViewModel
            LaunchedEffect(key1 = username) {
                viewModel.processIntent(WelcomeIntent.SetUsername(username))
            }

            // Handle navigation effect
            LaunchedEffect(key1 = true) {
                viewModel.effect.collect { effect ->
                    when (effect) {
                        is com.example.formfillingapp.mvi.model.WelcomeEffect.NavigateBack -> {
                            navController.popBackStack()
                        }
                    }
                }
            }

            WelcomeScreen(
                state = state,
                onIntent = viewModel::processIntent
            )
        }
    }
}
