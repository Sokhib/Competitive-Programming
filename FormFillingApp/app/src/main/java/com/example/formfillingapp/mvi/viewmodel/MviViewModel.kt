package com.example.formfillingapp.mvi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formfillingapp.mvi.intent.MviIntent
import com.example.formfillingapp.mvi.model.MviEffect
import com.example.formfillingapp.mvi.model.MviState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Base ViewModel for MVI architecture
 * Handles intents, updates state, and emits effects
 */
abstract class MviViewModel<I : MviIntent, S : MviState, E : MviEffect> : ViewModel() {

    // The current state of the UI
    private val _state = MutableStateFlow(initialState())
    val state: StateFlow<S> = _state.asStateFlow()

    // One-time events like navigation, showing a toast, etc.
    private val _effect = MutableSharedFlow<E>()
    val effect: SharedFlow<E> = _effect.asSharedFlow()

    // Initial state of the UI
    abstract fun initialState(): S

    // Process user intents
    fun processIntent(intent: I) {
        viewModelScope.launch {
            handleIntent(intent)
        }
    }

    // Handle user intents and update state or emit effects
    protected abstract suspend fun handleIntent(intent: I)

    // Update the current state
    protected fun updateState(reducer: S.() -> S) {
        val newState = state.value.reducer()
        _state.value = newState
    }

    // Emit a one-time effect
    protected suspend fun emitEffect(effect: E) {
        _effect.emit(effect)
    }
}