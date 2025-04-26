package com.example.formfillingapp.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.formfillingapp.mvi.intent.LoginIntent
import com.example.formfillingapp.mvi.model.LoginState

@Composable
fun LoginScreen(
    state: LoginState,
    onIntent: (LoginIntent) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    // Show error message in snackbar if present
    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let {
            snackbarHostState.showSnackbar(it)
            onIntent(LoginIntent.ClearError)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Login") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Welcome to Form Filling App",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Username field
                OutlinedTextField(
                    value = state.username,
                    onValueChange = { onIntent(LoginIntent.UpdateUsername(it)) },
                    label = { Text("Username") },
                    isError = state.isUsernameTouched && state.isUsernameError,
                    supportingText = {
                        if (state.isUsernameTouched && state.isUsernameError) {
                            Text(state.usernameErrorMessage ?: "Username is required")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            onIntent(LoginIntent.UsernameFocusChanged(focusState.isFocused))
                        },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password field
                OutlinedTextField(
                    value = state.password,
                    onValueChange = { onIntent(LoginIntent.UpdatePassword(it)) },
                    label = { Text("Password") },
                    isError = state.isPasswordTouched && state.isPasswordError,
                    supportingText = {
                        if (state.isPasswordTouched && state.isPasswordError) {
                            Text(state.passwordErrorMessage ?: "Password is required")
                        }
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            onIntent(LoginIntent.PasswordFocusChanged(focusState.isFocused))
                        },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Use derivedStateOf to optimize button enabled state
                val isFormValid by remember(state) {
                    derivedStateOf {
                        // Initially, if nothing is touched, allow button to be enabled
                        if (!state.isUsernameTouched && !state.isPasswordTouched) {
                            true
                        } else {
                            // Once fields are touched, validate them
                            val isUsernameValid = !state.isUsernameError && state.username.isNotBlank()
                            val isPasswordValid = !state.isPasswordError && state.password.isNotBlank()
                            isUsernameValid && isPasswordValid
                        }
                    }
                }

                // Login button
                Button(
                    onClick = { onIntent(LoginIntent.SubmitLogin) },
                    enabled = !state.isLoading && isFormValid,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login")
                }
            }

            // Loading indicator
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
