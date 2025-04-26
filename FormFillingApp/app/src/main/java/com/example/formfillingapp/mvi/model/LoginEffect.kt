package com.example.formfillingapp.mvi.model

/**
 * Effects for the login screen
 */
sealed class LoginEffect : MviEffect {
    /**
     * Navigate to the welcome screen
     */
    data class NavigateToWelcome(val username: String) : LoginEffect()

    /**
     * Show an error message
     */
    data class ShowError(val message: String) : LoginEffect()
}