package com.example.formfillingapp.mvi.viewmodel

import app.cash.turbine.test
import com.example.formfillingapp.mvi.intent.LoginIntent
import com.example.formfillingapp.mvi.model.LoginEffect
import com.example.formfillingapp.mvi.model.LoginState
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

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LoginViewModel()
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
    }

    @Test
    fun `update username updates state correctly`() = runTest {
        viewModel.processIntent(LoginIntent.UpdateUsername("testuser"))
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals("testuser", state.username)
        assertFalse(state.isUsernameError)
    }

    @Test
    fun `update password updates state correctly`() = runTest {
        viewModel.processIntent(LoginIntent.UpdatePassword("password123"))
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals("password123", state.password)
        assertFalse(state.isPasswordError)
    }

    @Test
    fun `submit login with empty fields shows errors`() = runTest {
        viewModel.processIntent(LoginIntent.SubmitLogin)
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state.isUsernameError)
        assertTrue(state.isPasswordError)
        // With our new ValidationManager, the error message will be from the first validator that fails
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
            assertFalse(state.isUsernameError)
            assertFalse(state.isPasswordError)
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
