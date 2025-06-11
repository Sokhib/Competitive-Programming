package com.example.formfillingapp.mvi.model

/**
 * State for the welcome screen
 */
data class WelcomeState(
    val username: String = "",
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null
) : MviState
