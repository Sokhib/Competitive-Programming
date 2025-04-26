package com.example.formfillingapp.navigation

/**
 * Navigation routes for the app
 */
object NavRoutes {
    const val LOGIN = "login"
    const val WELCOME = "welcome/{username}"

    /**
     * Create the welcome route with the username parameter
     */
    fun createWelcomeRoute(username: String): String {
        return "welcome/$username"
    }
}