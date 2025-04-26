package com.example.formfillingapp.navigation

import androidx.navigation.NavController
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Helper class for navigation operations
 * This class encapsulates navigation logic and can be injected where needed
 */
@Singleton
class NavigationHelper @Inject constructor() {

    /**
     * Navigate to the welcome screen
     *
     * @param navController The navigation controller to use
     * @param username The username to pass to the welcome screen
     */
    fun navigateToWelcome(navController: NavController, username: String) {
        navController.navigate(NavRoutes.createWelcomeRoute(username)) {
            popUpTo(NavRoutes.LOGIN) { inclusive = false }
        }
    }

    /**
     * Navigate back to the previous screen
     *
     * @param navController The navigation controller to use
     */
    fun navigateBack(navController: NavController) {
        navController.popBackStack()
    }
}