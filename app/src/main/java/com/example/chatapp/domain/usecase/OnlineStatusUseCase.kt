package com.example.chatapp.domain.usecase

import com.example.chatapp.domain.repository.ChatRepository
import javax.inject.Inject

class OnlineStatusUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend fun observeStatus(uid: String) = repository.observeUserStatus(uid)
}