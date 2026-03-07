package com.example.chatapp.domain.usecase

import com.example.chatapp.domain.repository.ChatRepository
import javax.inject.Inject

class GetMessageUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    operator fun invoke(chatId: String) = repository.getMessages(chatId)
}