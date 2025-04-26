package com.example.formfillingapp.mvi.viewmodel

import com.example.formfillingapp.mvi.intent.WelcomeIntent
import com.example.formfillingapp.mvi.model.WelcomeEffect
import com.example.formfillingapp.mvi.model.WelcomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor() : MviViewModel<WelcomeIntent, WelcomeState, WelcomeEffect>() {

    override fun initialState(): WelcomeState = WelcomeState()

    override suspend fun handleIntent(intent: WelcomeIntent) {
        when (intent) {
            is WelcomeIntent.SetUsername -> setUsername(intent.username)
            is WelcomeIntent.NavigateBack -> navigateBack()
        }
    }

    private fun setUsername(username: String) {
        updateState { copy(username = username) }
    }

    private suspend fun navigateBack() {
        emitEffect(WelcomeEffect.NavigateBack)
    }
}