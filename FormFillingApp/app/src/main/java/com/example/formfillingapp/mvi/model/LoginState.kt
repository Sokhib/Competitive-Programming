package com.example.formfillingapp.mvi.model

/**
 * State for the login screen
 */
data class LoginState(
    val username: String = "",
    val password: String = "",
    val isUsernameError: Boolean = false,
    val isPasswordError: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) : MviState