package com.example.formfillingapp.mvi.viewmodel

import com.example.formfillingapp.mvi.intent.LoginIntent
import com.example.formfillingapp.mvi.model.LoginEffect
import com.example.formfillingapp.mvi.model.LoginState
import com.example.formfillingapp.validation.NotBlankValidator
import com.example.formfillingapp.validation.ValidationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : MviViewModel<LoginIntent, LoginState, LoginEffect>() {

    // Validators for username and password using different approaches

    // Approach 1: Using the forString factory method (original approach)
    private val usernameValidator = ValidationManager.forString(
        required = true,
        requiredMessage = "Username is required"
    )

    // Approach 2: Using the builder pattern
    private val passwordValidator = ValidationManager.Builder<String>()
        .addValidator(NotBlankValidator("Password is required"))
        .addValidation("Password must be at least 6 characters") { it.length >= 6 }
        .build()

    // Approach 3: Using the create method with function callbacks
    private val formValidator = ValidationManager.create<LoginState>()
        .addValidation("Both username and password are required") {
            it.username.isNotBlank() && it.password.isNotBlank()
        }
        .addValidation("Password must be at least 6 characters") {
            it.password.length >= 6 || it.password.isBlank() // Only validate if not blank
        }

    override fun initialState(): LoginState = LoginState()

    override suspend fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.UpdateUsername -> updateUsername(intent.username)
            is LoginIntent.UpdatePassword -> updatePassword(intent.password)
            is LoginIntent.SubmitLogin -> submitLogin()
            is LoginIntent.ClearError -> clearError()
        }
    }

    private fun updateUsername(username: String) {
        updateState { copy(username = username, isUsernameError = false) }
    }

    private fun updatePassword(password: String) {
        updateState { copy(password = password, isPasswordError = false) }
    }

    private suspend fun submitLogin() {
        // Validate individual fields
        val usernameResult = usernameValidator.validate(state.value.username)
        val passwordResult = passwordValidator.validate(state.value.password)

        // Validate the entire form state
        val formResult = formValidator.validate(state.value)

        // Check if all validations passed
        if (!usernameResult.isValid || !passwordResult.isValid || !formResult.isValid) {
            updateState {
                copy(
                    isUsernameError = !usernameResult.isValid,
                    isPasswordError = !passwordResult.isValid,
                    errorMessage = usernameResult.errorMessage
                        ?: passwordResult.errorMessage
                        ?: formResult.errorMessage
                        ?: "Validation failed"
                )
            }
            return
        }

        // Show loading
        updateState { copy(isLoading = true, errorMessage = null) }

        try {
            // Simulate network delay
            kotlinx.coroutines.delay(1000)

            // Navigate to welcome screen
            emitEffect(LoginEffect.NavigateToWelcome(state.value.username))

            // Reset loading state
            updateState { copy(isLoading = false) }
        } catch (e: Exception) {
            // Handle error
            updateState {
                copy(
                    isLoading = false,
                    errorMessage = e.message ?: "An error occurred"
                )
            }
            emitEffect(LoginEffect.ShowError(e.message ?: "An error occurred"))
        }
    }

    private fun clearError() {
        updateState { copy(errorMessage = null) }
    }
}
