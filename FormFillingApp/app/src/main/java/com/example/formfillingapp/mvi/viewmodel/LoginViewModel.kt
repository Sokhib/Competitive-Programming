package com.example.formfillingapp.mvi.viewmodel

import com.example.formfillingapp.mvi.intent.LoginIntent
import com.example.formfillingapp.mvi.model.LoginEffect
import com.example.formfillingapp.mvi.model.LoginState
import com.example.formfillingapp.validation.MinLengthValidator
import com.example.formfillingapp.validation.NotBlankValidator
import com.example.formfillingapp.validation.ValidationManager
import com.example.formfillingapp.validation.ValidationManagerFactory
import com.example.formfillingapp.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validationManagerFactory: ValidationManagerFactory,
    private val notBlankValidator: NotBlankValidator,
    private val minLengthValidator: MinLengthValidator,
    private val logger: Logger
) : MviViewModel<LoginIntent, LoginState, LoginEffect>() {

    companion object {
        private const val TAG = "LoginViewModel"
    }

    // Validators for username and password using different approaches

    // Approach 1: Using the forString factory method (original approach)
    private val usernameValidator = validationManagerFactory.forString(
        required = true,
        requiredMessage = "Username is required"
    )

    // Approach 2: Using the builder pattern with injected validators
    private val passwordValidator = validationManagerFactory.create<String>()
        .addValidator(notBlankValidator)
        .addValidator(minLengthValidator)

    // Approach 3: Using the create method with function callbacks
    private val formValidator = validationManagerFactory.create<LoginState>()
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
            is LoginIntent.UsernameFocusChanged -> handleUsernameFocus(intent.isFocused)
            is LoginIntent.PasswordFocusChanged -> handlePasswordFocus(intent.isFocused)
        }
    }

    private fun updateUsername(username: String) {
        updateState { copy(username = username) }

        // Only validate if the field has been touched
        if (state.value.isUsernameTouched) {
            validateUsername()
        }
    }

    private fun updatePassword(password: String) {
        updateState { copy(password = password) }

        // Only validate if the field has been touched
        if (state.value.isPasswordTouched) {
            validatePassword()
        }
    }

    private fun handleUsernameFocus(isFocused: Boolean) {
        // When focus is lost, mark the field as touched and validate
        if (!isFocused) {
            updateState { copy(isUsernameTouched = true) }
            validateUsername()
        }
    }

    private fun handlePasswordFocus(isFocused: Boolean) {
        // When focus is lost, mark the field as touched and validate
        if (!isFocused) {
            updateState { copy(isPasswordTouched = true) }
            validatePassword()
        }
    }

    private fun validateUsername() {
        val result = usernameValidator.validate(state.value.username)
        updateState {
            copy(
                isUsernameError = !result.isValid,
                usernameErrorMessage = result.errorMessage
            )
        }
    }

    private fun validatePassword() {
        val result = passwordValidator.validate(state.value.password)
        updateState {
            copy(
                isPasswordError = !result.isValid,
                passwordErrorMessage = result.errorMessage
            )
        }
    }

    private suspend fun submitLogin() {
        logger.debug(TAG, "Login attempt with username: ${state.value.username}")

        // Mark both fields as touched to show validation errors
        updateState {
            copy(
                isUsernameTouched = true,
                isPasswordTouched = true
            )
        }

        // Validate individual fields
        val usernameResult = usernameValidator.validate(state.value.username)
        val passwordResult = passwordValidator.validate(state.value.password)

        // Validate the entire form state
        val formResult = formValidator.validate(state.value)

        // Check if all validations passed
        if (!usernameResult.isValid || !passwordResult.isValid || !formResult.isValid) {
            logger.warn(
                TAG,
                "Validation failed: ${usernameResult.errorMessage ?: passwordResult.errorMessage ?: formResult.errorMessage}"
            )
            updateState {
                copy(
                    isUsernameError = !usernameResult.isValid,
                    isPasswordError = !passwordResult.isValid,
                    usernameErrorMessage = usernameResult.errorMessage,
                    passwordErrorMessage = passwordResult.errorMessage,
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
        updateState {
            copy(
                errorMessage = null,
                // We don't clear the error flags or touched state,
                // just the general error message for the snackbar
            )
        }
    }
}
