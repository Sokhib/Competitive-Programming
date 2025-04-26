package com.example.formfillingapp.mvi.viewmodel

import app.cash.turbine.test
import com.example.formfillingapp.mvi.intent.LoginIntent
import com.example.formfillingapp.mvi.model.LoginEffect
import com.example.formfillingapp.mvi.model.LoginState
import com.example.formfillingapp.utils.Logger
import com.example.formfillingapp.validation.MinLengthValidator
import com.example.formfillingapp.validation.NotBlankValidator
import com.example.formfillingapp.validation.ValidationManager
import com.example.formfillingapp.validation.ValidationManagerFactory
import com.example.formfillingapp.validation.ValidationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private val testDispatcher = StandardTestDispatcher()

    // Mock dependencies
    private lateinit var validationManagerFactory: ValidationManagerFactory
    private lateinit var notBlankValidator: NotBlankValidator
    private lateinit var minLengthValidator: MinLengthValidator
    private lateinit var logger: Logger

    // Mock validation managers
    private lateinit var usernameValidator: ValidationManager<String>
    private lateinit var passwordValidator: ValidationManager<String>
    private lateinit var formValidator: ValidationManager<LoginState>

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        // Create mocks
        validationManagerFactory = mock(ValidationManagerFactory::class.java)
        notBlankValidator = mock(NotBlankValidator::class.java)
        minLengthValidator = mock(MinLengthValidator::class.java)
        logger = mock(Logger::class.java)

        // Create mock validation managers
        usernameValidator = mock(ValidationManager::class.java) as ValidationManager<String>
        passwordValidator = mock(ValidationManager::class.java) as ValidationManager<String>
        formValidator = mock(ValidationManager::class.java) as ValidationManager<LoginState>

        // Set up validationManagerFactory to return mock validators
        `when`(
            validationManagerFactory.forString(
                required = true,
                requiredMessage = "Username is required"
            )
        ).thenReturn(usernameValidator)

        `when`(validationManagerFactory.create<String>()).thenReturn(passwordValidator)
        `when`(passwordValidator.addValidator(notBlankValidator)).thenReturn(passwordValidator)
        `when`(passwordValidator.addValidator(minLengthValidator)).thenReturn(passwordValidator)

        `when`(validationManagerFactory.create<LoginState>()).thenReturn(formValidator)
        `when`(
            formValidator.addValidation(
                org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.any()
            )
        ).thenReturn(formValidator)

        // Set up default validation results
        `when`(usernameValidator.validate("")).thenReturn(ValidationResult.error("Username is required"))
        `when`(usernameValidator.validate("testuser")).thenReturn(ValidationResult.success())

        `when`(passwordValidator.validate("")).thenReturn(ValidationResult.error("Password is required"))
        `when`(passwordValidator.validate("pass")).thenReturn(ValidationResult.error("Password must be at least 6 characters"))
        `when`(passwordValidator.validate("password123")).thenReturn(ValidationResult.success())

        `when`(formValidator.validate(org.mockito.ArgumentMatchers.any())).thenReturn(ValidationResult.success())

        // Create viewModel with mocked dependencies
        viewModel = LoginViewModel(
            validationManagerFactory,
            notBlankValidator,
            minLengthValidator,
            logger
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is correct`() = runTest {
        val initialState = viewModel.state.value
        assertEquals("", initialState.username)
        assertEquals("", initialState.password)
        assertFalse(initialState.isUsernameError)
        assertFalse(initialState.isPasswordError)
        assertFalse(initialState.isLoading)
        assertNull(initialState.errorMessage)
        assertFalse(initialState.isUsernameTouched)
        assertFalse(initialState.isPasswordTouched)
        assertNull(initialState.usernameErrorMessage)
        assertNull(initialState.passwordErrorMessage)
    }

    @Test
    fun `update username updates state correctly`() = runTest {
        viewModel.processIntent(LoginIntent.UpdateUsername("testuser"))
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals("testuser", state.username)
        assertFalse(state.isUsernameError)
        assertFalse(state.isUsernameTouched)
        assertNull(state.usernameErrorMessage)
    }

    @Test
    fun `update password updates state correctly`() = runTest {
        viewModel.processIntent(LoginIntent.UpdatePassword("password123"))
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals("password123", state.password)
        assertFalse(state.isPasswordError)
        assertFalse(state.isPasswordTouched)
        assertNull(state.passwordErrorMessage)
    }

    @Test
    fun `username focus change marks field as touched and validates`() = runTest {
        // Set username and then lose focus
        viewModel.processIntent(LoginIntent.UpdateUsername(""))
        viewModel.processIntent(LoginIntent.UsernameFocusChanged(false))
        testDispatcher.scheduler.advanceUntilIdle()

        // Verify field is marked as touched and validation error is shown
        val state = viewModel.state.value
        assertTrue(state.isUsernameTouched)
        assertTrue(state.isUsernameError)
        assertEquals("Username is required", state.usernameErrorMessage)

        // Set valid username and verify error is cleared
        viewModel.processIntent(LoginIntent.UpdateUsername("testuser"))
        testDispatcher.scheduler.advanceUntilIdle()

        val updatedState = viewModel.state.value
        assertTrue(updatedState.isUsernameTouched)
        assertFalse(updatedState.isUsernameError)
        assertNull(updatedState.usernameErrorMessage)
    }

    @Test
    fun `password focus change marks field as touched and validates`() = runTest {
        // Set password and then lose focus
        viewModel.processIntent(LoginIntent.UpdatePassword("pass"))
        viewModel.processIntent(LoginIntent.PasswordFocusChanged(false))
        testDispatcher.scheduler.advanceUntilIdle()

        // Verify field is marked as touched and validation error is shown
        val state = viewModel.state.value
        assertTrue(state.isPasswordTouched)
        assertTrue(state.isPasswordError)
        assertEquals("Password must be at least 6 characters", state.passwordErrorMessage)

        // Set valid password and verify error is cleared
        viewModel.processIntent(LoginIntent.UpdatePassword("password123"))
        testDispatcher.scheduler.advanceUntilIdle()

        val updatedState = viewModel.state.value
        assertTrue(updatedState.isPasswordTouched)
        assertFalse(updatedState.isPasswordError)
        assertNull(updatedState.passwordErrorMessage)
    }

    @Test
    fun `validation only happens after field is touched`() = runTest {
        // Update with invalid values but don't touch fields
        viewModel.processIntent(LoginIntent.UpdateUsername(""))
        viewModel.processIntent(LoginIntent.UpdatePassword("pass"))
        testDispatcher.scheduler.advanceUntilIdle()

        // Verify no validation errors are shown
        val state = viewModel.state.value
        assertFalse(state.isUsernameTouched)
        assertFalse(state.isPasswordTouched)
        assertFalse(state.isUsernameError)
        assertFalse(state.isPasswordError)
        assertNull(state.usernameErrorMessage)
        assertNull(state.passwordErrorMessage)
    }

    @Test
    fun `submit login with empty fields shows errors and marks fields as touched`() = runTest {
        viewModel.processIntent(LoginIntent.SubmitLogin)
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state.isUsernameTouched)
        assertTrue(state.isPasswordTouched)
        assertTrue(state.isUsernameError)
        assertTrue(state.isPasswordError)
        assertEquals("Username is required", state.usernameErrorMessage)
        assertEquals("Password is required", state.passwordErrorMessage)
        assertEquals("Username is required", state.errorMessage)
    }

    @Test
    fun `submit login with valid fields navigates to welcome screen`() = runTest {
        // Set username and password
        viewModel.processIntent(LoginIntent.UpdateUsername("testuser"))
        viewModel.processIntent(LoginIntent.UpdatePassword("password123"))
        testDispatcher.scheduler.advanceUntilIdle()

        // Collect effects
        viewModel.effect.test {
            // Submit login
            viewModel.processIntent(LoginIntent.SubmitLogin)
            testDispatcher.scheduler.advanceUntilIdle()

            // Verify effect
            val effect = awaitItem()
            assertTrue(effect is LoginEffect.NavigateToWelcome)
            assertEquals("testuser", (effect as LoginEffect.NavigateToWelcome).username)

            // Verify state
            val state = viewModel.state.value
            assertFalse(state.isLoading)
            assertTrue(state.isUsernameTouched)
            assertTrue(state.isPasswordTouched)
            assertFalse(state.isUsernameError)
            assertFalse(state.isPasswordError)
            assertNull(state.usernameErrorMessage)
            assertNull(state.passwordErrorMessage)
            assertNull(state.errorMessage)
        }
    }

    @Test
    fun `clear error clears error message`() = runTest {
        // Set error state
        viewModel.processIntent(LoginIntent.SubmitLogin)
        testDispatcher.scheduler.advanceUntilIdle()

        // Verify error is set
        val stateWithError = viewModel.state.value
        assertEquals("Username is required", stateWithError.errorMessage)

        // Clear error
        viewModel.processIntent(LoginIntent.ClearError)
        testDispatcher.scheduler.advanceUntilIdle()

        // Verify error is cleared
        val stateAfterClear = viewModel.state.value
        assertNull(stateAfterClear.errorMessage)
    }
}
