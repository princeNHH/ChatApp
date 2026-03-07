package com.example.chatapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

    private val loginUseCase: LoginUseCase

) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)

    val loginState: StateFlow<LoginState> = _loginState


    fun login(email: String, password: String) {

        viewModelScope.launch {

            _loginState.value = LoginState.Loading

            val result = loginUseCase(email, password)

            result.onSuccess {

                _loginState.value = LoginState.Success(it)

            }

            result.onFailure {

                _loginState.value =
                    LoginState.Error(it.message)

            }

        }

    }

}