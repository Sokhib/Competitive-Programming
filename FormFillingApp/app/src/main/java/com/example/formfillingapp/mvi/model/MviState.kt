package com.example.formfillingapp.mvi.model

/**
 * Base interface for MVI State
 * Represents the current state of the UI
 */
interface MviState {
    /**
     * Whether the UI is in a loading state
     */
    val isLoading: Boolean

    /**
     * Error message to display, or null if there is no error
     */
    val errorMessage: String?
}
