package com.example.formfillingapp.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.formfillingapp.ui.theme.FormFillingAppTheme
import org.junit.Rule
import org.junit.Test

class AppNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun navigation_loginToWelcomeAndBack() {
        // Start with the app navigation
        composeTestRule.setContent {
            FormFillingAppTheme {
                AppNavigation()
            }
        }

        // Verify we're on the login screen
        composeTestRule.onNodeWithText("Login").assertIsDisplayed()
        composeTestRule.onNodeWithText("Welcome to Form Filling App").assertIsDisplayed()

        // Enter username and password
        composeTestRule.onNodeWithText("Username").performTextInput("testuser")
        composeTestRule.onNodeWithText("Password").performTextInput("password123")

        // Click login button
        composeTestRule.onNodeWithText("Login", useUnmergedTree = true).performClick()

        // Wait for navigation animation
        composeTestRule.waitForIdle()

        // Verify we're on the welcome screen
        composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
        composeTestRule.onNodeWithText("Welcome, testuser!").assertIsDisplayed()
        composeTestRule.onNodeWithText("You have successfully logged in.").assertIsDisplayed()

        // Click back button
        composeTestRule.onNodeWithText("Go Back").performClick()

        // Wait for navigation animation
        composeTestRule.waitForIdle()

        // Verify we're back on the login screen
        composeTestRule.onNodeWithText("Login").assertIsDisplayed()
        composeTestRule.onNodeWithText("Welcome to Form Filling App").assertIsDisplayed()
    }
}