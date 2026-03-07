package com.example.chatapp.data.remote

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class ChatStorageDataSource @Inject constructor(
    private val storage: FirebaseStorage
) {
    suspend fun uploadImage(roomId: String, imageUri: Uri): String {
        val ref = storage.reference
            .child("chat_media/$roomId/${UUID.randomUUID()}.jpg")
        ref.putFile(imageUri).await()
        return ref.downloadUrl.await().toString()
    }
}
