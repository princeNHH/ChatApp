package com.example.chatapp.domain.repository

import android.net.Uri
import com.example.chatapp.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun sendMessage(roomId: String, message: Message)
    fun observeMessages(roomId: String): Flow<List<Message>>
    suspend fun uploadImage(roomId: String, imageUri: Uri): String
    suspend fun updateOnlineStatus(uid: String, online: Boolean)
}