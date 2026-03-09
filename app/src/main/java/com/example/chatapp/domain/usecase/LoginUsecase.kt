package com.example.chatapp.domain.usecase

import com.example.chatapp.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<String> {

        if (email.isBlank() || password.isBlank()) {
            return Result.failure(
                IllegalArgumentException("Email or password empty")
            )
        }
        return repository.login(email, password)
    }

    fun currentUser() = repository.currentUser()
}