package com.example.chatapp.data.remote

import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class OnlineStatusDataSource @Inject constructor(
    private val database: FirebaseDatabase
) {
    suspend fun updateOnlineStatus(uid: String, online: Boolean) {
        val ref = database.getReference("status")
            .child(uid)
        ref.child("online").setValue(online)
        if (!online) {
            ref.child("last_active").setValue(System.currentTimeMillis())
        }
    }
}