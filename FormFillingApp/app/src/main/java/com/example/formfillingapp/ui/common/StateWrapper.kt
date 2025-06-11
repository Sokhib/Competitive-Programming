package com.example.formfillingapp.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.foundation.layout.padding
import com.example.formfillingapp.mvi.model.MviState

/**
 * A wrapper composable that handles common state-related UI patterns:
 * - Displaying a loading indicator when state is loading
 * - Showing error messages in a snackbar
 *
 * @param state The MVI state that implements MviState
 * @param onClearError Optional callback to clear the error message after it's shown
 * @param topBar Optional composable for the top app bar
 * @param content The main content to display
 */
@Composable
fun <S : MviState> StateWrapper(
    state: S,
    onClearError: (() -> Unit)? = null,
    topBar: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    // Show error message in snackbar if present
    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let {
            snackbarHostState.showSnackbar(it)
            onClearError?.invoke()
        }
    }

    Scaffold(
        topBar = topBar,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Main content
            content()

            // Loading indicator
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .testTag("loadingIndicator")
                )
            }
        }
    }
}
