package com.example.chatapp.presentation.login

sealed class LoginState {
    object Idle : LoginState()

    object Loading : LoginState()

    data class Success(
        val userId: String
    ) : LoginState()

    data class Error(
        val message: String?
    ) : LoginState()
}