package com.example.formfillingapp.mvi.viewmodel

import app.cash.turbine.test
import com.example.formfillingapp.mvi.intent.WelcomeIntent
import com.example.formfillingapp.mvi.model.WelcomeEffect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class WelcomeViewModelTest {

    private lateinit var viewModel: WelcomeViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = WelcomeViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is correct`() = runTest {
        val initialState = viewModel.state.value
        assertEquals("", initialState.username)
        assertEquals(false, initialState.isLoading)
        assertEquals(null, initialState.errorMessage)
    }

    @Test
    fun `set username updates state correctly`() = runTest {
        viewModel.processIntent(WelcomeIntent.SetUsername("testuser"))
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals("testuser", state.username)
    }

    @Test
    fun `navigate back emits navigate back effect`() = runTest {
        viewModel.effect.test {
            viewModel.processIntent(WelcomeIntent.NavigateBack)
            testDispatcher.scheduler.advanceUntilIdle()

            val effect = awaitItem()
            assertTrue(effect is WelcomeEffect.NavigateBack)
        }
    }

    @Test
    fun `navigate back sets loading state`() = runTest {
        // When navigating back
        viewModel.processIntent(WelcomeIntent.NavigateBack)

        // Then loading state should be set to true initially
        assertTrue(viewModel.state.value.isLoading)

        // After navigation completes, loading state should be false
        testDispatcher.scheduler.advanceUntilIdle()
        assertFalse(viewModel.state.value.isLoading)
    }
}
