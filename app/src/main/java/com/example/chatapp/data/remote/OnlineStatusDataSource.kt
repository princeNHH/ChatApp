package com.example.chatapp.data.remote

import com.example.chatapp.domain.model.UserStatus
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class OnlineStatusDataSource @Inject constructor(
    private val database: FirebaseDatabase
) {
    fun updateOnlineStatus(uid: String) {

        val statusRef = database
            .getReference("status")
            .child(uid)

        val connectedRef = database.getReference(".info/connected")
        connectedRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val onlineState = mapOf(
                    "online" to true,
                    "lastSeen" to ServerValue.TIMESTAMP
                )

                val offlineState = mapOf(
                    "online" to false,
                    "lastSeen" to ServerValue.TIMESTAMP
                )

                statusRef.updateChildren(onlineState)

                statusRef.onDisconnect().updateChildren(offlineState)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun observeUserStatus(uid: String) = callbackFlow {
        val ref = database.getReference("status")
            .child(uid)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val online = snapshot.child("online").getValue(Boolean::class.java) ?: false
                val lastSeen = snapshot.child("lastSeen").getValue(Long::class.java) ?: 0L
                trySend(UserStatus(online, lastSeen))
            }
            override fun onCancelled(error: DatabaseError) {}
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }
}