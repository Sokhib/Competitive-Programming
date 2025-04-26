package com.example.formfillingapp.ui.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.formfillingapp.mvi.intent.LoginIntent
import com.example.formfillingapp.mvi.model.LoginState
import com.example.formfillingapp.ui.theme.FormFillingAppTheme
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginScreen_displaysAllElements() {
        // Given
        val state = LoginState()
        val onIntent: (LoginIntent) -> Unit = mock()

        // When
        composeTestRule.setContent {
            FormFillingAppTheme {
                LoginScreen(state = state, onIntent = onIntent)
            }
        }

        // Then
        composeTestRule.onNodeWithText("Login").assertIsDisplayed()
        composeTestRule.onNodeWithText("Welcome to Form Filling App").assertIsDisplayed()
        composeTestRule.onNodeWithText("Username").assertIsDisplayed()
        composeTestRule.onNodeWithText("Password").assertIsDisplayed()
        composeTestRule.onNodeWithText("Login", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun loginScreen_inputFieldsUpdateState() {
        // Given
        val state = LoginState()
        val onIntent: (LoginIntent) -> Unit = mock()

        // When
        composeTestRule.setContent {
            FormFillingAppTheme {
                LoginScreen(state = state, onIntent = onIntent)
            }
        }

        // Then
        composeTestRule.onNodeWithText("Username").performTextInput("testuser")
        verify(onIntent).invoke(LoginIntent.UpdateUsername("testuser"))

        composeTestRule.onNodeWithText("Password").performTextInput("password123")
        verify(onIntent).invoke(LoginIntent.UpdatePassword("password123"))
    }

    @Test
    fun loginScreen_loginButtonClicksSubmitIntent() {
        // Given
        val state = LoginState()
        val onIntent: (LoginIntent) -> Unit = mock()

        // When
        composeTestRule.setContent {
            FormFillingAppTheme {
                LoginScreen(state = state, onIntent = onIntent)
            }
        }

        // Then
        composeTestRule.onNodeWithText("Login", useUnmergedTree = true).performClick()
        verify(onIntent).invoke(LoginIntent.SubmitLogin)
    }

    @Test
    fun loginScreen_showsErrorMessages() {
        // Given
        val state = LoginState(
            isUsernameError = true,
            isPasswordError = true
        )
        val onIntent: (LoginIntent) -> Unit = mock()

        // When
        composeTestRule.setContent {
            FormFillingAppTheme {
                LoginScreen(state = state, onIntent = onIntent)
            }
        }

        // Then
        composeTestRule.onNodeWithText("Username is required").assertIsDisplayed()
        composeTestRule.onNodeWithText("Password is required").assertIsDisplayed()
    }

    @Test
    fun loginScreen_disablesButtonWhenLoading() {
        // Given
        val state = LoginState(isLoading = true)
        val onIntent: (LoginIntent) -> Unit = mock()

        // When
        composeTestRule.setContent {
            FormFillingAppTheme {
                LoginScreen(state = state, onIntent = onIntent)
            }
        }

        // Then
        composeTestRule.onNodeWithText("Login", useUnmergedTree = true).assertIsNotEnabled()
    }

    @Test
    fun loginScreen_enablesButtonWhenNotLoading() {
        // Given
        val state = LoginState(isLoading = false)
        val onIntent: (LoginIntent) -> Unit = mock()

        // When
        composeTestRule.setContent {
            FormFillingAppTheme {
                LoginScreen(state = state, onIntent = onIntent)
            }
        }

        // Then
        composeTestRule.onNodeWithText("Login", useUnmergedTree = true).assertIsEnabled()
    }
}