package com.example.formfillingapp.mvi.model

/**
 * State for the welcome screen
 */
data class WelcomeState(
    val username: String = ""
) : MviState