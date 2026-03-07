package com.example.chatapp.domain.repository

import com.example.chatapp.domain.model.User

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): Result<String>

    fun currentUser(): User?
    suspend fun logout()
}