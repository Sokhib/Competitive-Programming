package com.example.formfillingapp.mvi.intent

/**
 * Intents for the login screen
 */
sealed class LoginIntent : MviIntent {
    /**
     * Update the username field
     */
    data class UpdateUsername(val username: String) : LoginIntent()

    /**
     * Update the password field
     */
    data class UpdatePassword(val password: String) : LoginIntent()

    /**
     * Submit the login form
     */
    object SubmitLogin : LoginIntent()

    /**
     * Clear any error messages
     */
    object ClearError : LoginIntent()

    /**
     * Username field focus changed
     */
    data class UsernameFocusChanged(val isFocused: Boolean) : LoginIntent()

    /**
     * Password field focus changed
     */
    data class PasswordFocusChanged(val isFocused: Boolean) : LoginIntent()
}
