package com.example.formfillingapp.mvi.intent

/**
 * Intents for the welcome screen
 */
sealed class WelcomeIntent : MviIntent {
    /**
     * Set the username to display
     */
    data class SetUsername(val username: String) : WelcomeIntent()

    /**
     * Navigate back to the login screen
     */
    object NavigateBack : WelcomeIntent()
}