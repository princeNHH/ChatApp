package com.example.chatapp.data.remote

import com.example.chatapp.domain.model.Message
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChatFirestoreDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    fun observeMessages(roomId: String) = callbackFlow {
        val listener = firestore.collection("rooms")
            .document(roomId)
            .collection("messages")
            .orderBy("timestamp")
            .addSnapshotListener { snapshot, _ ->
                val list = snapshot?.toObjects(Message::class.java) ?: emptyList()
                trySend(list)
            }
        awaitClose { listener.remove() }
    }

    suspend fun sendMessage(roomId: String, message: Message) {
        val roomRef = firestore.collection("rooms").document(roomId)
        val messageRef = roomRef.collection("messages").document()
        val msg = message.copy(id = messageRef.id)
        firestore.runBatch { batch ->
            batch.set(messageRef, msg)
            batch.update(
                roomRef, "lastMessage", mapOf(
                    "text" to message.text,
                    "senderId" to message.senderId,
                    "sendAt" to message.timestamp
                )
            )
        }.await()
    }
}