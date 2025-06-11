package com.example.formfillingapp.ui.welcome

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.formfillingapp.mvi.intent.WelcomeIntent
import com.example.formfillingapp.mvi.model.WelcomeState
import com.example.formfillingapp.ui.theme.FormFillingAppTheme
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class WelcomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun welcomeScreen_displaysAllElements() {
        // Given
        val state = WelcomeState(username = "testuser")
        val onIntent: (WelcomeIntent) -> Unit = mock()

        // When
        composeTestRule.setContent {
            FormFillingAppTheme {
                WelcomeScreen(state = state, onIntent = onIntent)
            }
        }

        // Then
        composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
        composeTestRule.onNodeWithText("Welcome, testuser!").assertIsDisplayed()
        composeTestRule.onNodeWithText("You have successfully logged in.").assertIsDisplayed()
        composeTestRule.onNodeWithText("Go Back").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Navigate back").assertIsDisplayed()
    }

    @Test
    fun welcomeScreen_backButtonClicksNavigateBackIntent() {
        // Given
        val state = WelcomeState(username = "testuser")
        val onIntent: (WelcomeIntent) -> Unit = mock()

        // When
        composeTestRule.setContent {
            FormFillingAppTheme {
                WelcomeScreen(state = state, onIntent = onIntent)
            }
        }

        // Then
        composeTestRule.onNodeWithText("Go Back").performClick()
        verify(onIntent).invoke(WelcomeIntent.NavigateBack)
    }

    @Test
    fun welcomeScreen_backIconClicksNavigateBackIntent() {
        // Given
        val state = WelcomeState(username = "testuser")
        val onIntent: (WelcomeIntent) -> Unit = mock()

        // When
        composeTestRule.setContent {
            FormFillingAppTheme {
                WelcomeScreen(state = state, onIntent = onIntent)
            }
        }

        // Then
        composeTestRule.onNodeWithContentDescription("Navigate back").performClick()
        verify(onIntent).invoke(WelcomeIntent.NavigateBack)
    }

    @Test
    fun welcomeScreen_displaysCorrectUsername() {
        // Given
        val state = WelcomeState(username = "johndoe")
        val onIntent: (WelcomeIntent) -> Unit = mock()

        // When
        composeTestRule.setContent {
            FormFillingAppTheme {
                WelcomeScreen(state = state, onIntent = onIntent)
            }
        }

        // Then
        composeTestRule.onNodeWithText("Welcome, johndoe!").assertIsDisplayed()
    }

    @Test
    fun welcomeScreen_showsLoadingIndicatorWhenLoading() {
        // Given
        val state = WelcomeState(username = "testuser", isLoading = true)
        val onIntent: (WelcomeIntent) -> Unit = mock()

        // When
        composeTestRule.setContent {
            FormFillingAppTheme {
                WelcomeScreen(state = state, onIntent = onIntent)
            }
        }

        // Then
        composeTestRule.onNodeWithTag("loadingIndicator").assertIsDisplayed()
        composeTestRule.onNodeWithText("Go Back").assertIsNotEnabled()
    }

    @Test
    fun welcomeScreen_enablesBackButtonWhenNotLoading() {
        // Given
        val state = WelcomeState(username = "testuser", isLoading = false)
        val onIntent: (WelcomeIntent) -> Unit = mock()

        // When
        composeTestRule.setContent {
            FormFillingAppTheme {
                WelcomeScreen(state = state, onIntent = onIntent)
            }
        }

        // Then
        composeTestRule.onNodeWithText("Go Back").assertIsEnabled()
    }
}
