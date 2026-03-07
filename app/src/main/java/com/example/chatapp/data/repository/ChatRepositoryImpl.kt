package com.example.chatapp.data.repository

import com.example.chatapp.data.remote.ChatRemoteDataSource
import com.example.chatapp.domain.model.Message
import com.example.chatapp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val remote: ChatRemoteDataSource,
) : ChatRepository {
    override suspend fun sendMessage(message: Message) {
        remote.sendMessage(message)
    }

    override fun getMessages(chatId: String): Flow<List<Message>> {
        return remote.getMessages(chatId)
    }

}