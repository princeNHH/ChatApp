package com.example.chatapp.data.repository

import com.example.chatapp.data.remote.AuthRemoteDataSource
import com.example.chatapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remote: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<String> {

        return remote.login(email, password)

    }

}