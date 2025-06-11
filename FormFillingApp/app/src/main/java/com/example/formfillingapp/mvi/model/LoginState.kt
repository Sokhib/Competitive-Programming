package com.example.formfillingapp.mvi.model

/**
 * State for the login screen
 */
data class LoginState(
    val username: String = "",
    val password: String = "",
    val isUsernameError: Boolean = false,
    val isPasswordError: Boolean = false,
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val isUsernameTouched: Boolean = false,
    val isPasswordTouched: Boolean = false,
    val usernameErrorMessage: String? = null,
    val passwordErrorMessage: String? = null
) : MviState
