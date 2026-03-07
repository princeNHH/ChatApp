package com.example.chatapp.domain.repository

import com.example.chatapp.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun sendMessage(message: Message)
    fun getMessages(chatId: String): Flow<List<Message>>
}