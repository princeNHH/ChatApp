package com.example.chatapp.data.repository

import android.net.Uri
import com.example.chatapp.data.remote.ChatFirestoreDataSource
import com.example.chatapp.data.remote.ChatStorageDataSource
import com.example.chatapp.data.remote.OnlineStatusDataSource
import com.example.chatapp.domain.model.Message
import com.example.chatapp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val firestore: ChatFirestoreDataSource,
    private val storage: ChatStorageDataSource,
    private val realtime: OnlineStatusDataSource
) : ChatRepository {
    override suspend fun sendMessage(roomId: String, message: Message) {
        firestore.sendMessage(roomId, message)
    }

    override fun observeMessages(roomId: String): Flow<List<Message>> {
        return firestore.observeMessages(roomId)
    }

    override suspend fun uploadImage(
        roomId: String,
        imageUri: Uri
    ): String {
        return storage.uploadImage(roomId, imageUri)
    }

    override suspend fun updateOnlineStatus(uid: String, online: Boolean) {
        realtime.updateOnlineStatus(uid, online)
    }

}