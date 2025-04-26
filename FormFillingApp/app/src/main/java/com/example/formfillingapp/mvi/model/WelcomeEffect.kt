package com.example.formfillingapp.mvi.model

/**
 * Effects for the welcome screen
 */
sealed class WelcomeEffect : MviEffect {
    /**
     * Navigate back to the login screen
     */
    object NavigateBack : WelcomeEffect()
}