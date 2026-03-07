package com.example.chatapp.data.remote

import com.example.chatapp.domain.model.Message
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ChatRemoteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    fun getMessages(chatId: String): Flow<List<Message>> = callbackFlow {
        val listener = firestore.collection("messages")
            .orderBy("timestamp")
            .addSnapshotListener { snapshot, error ->
                val messages = snapshot?.documents?.mapNotNull {
                    it.toObject(Message::class.java)
                }
                    ?: emptyList()
                trySend(messages)
            }
        awaitClose { listener.remove() }
    }

    suspend fun sendMessage(message: Message) {
        val docRef = firestore.collection("messages").document()
        val messageWithId = message.copy(id = docRef.id)
        docRef.set(messageWithId)
    }
}