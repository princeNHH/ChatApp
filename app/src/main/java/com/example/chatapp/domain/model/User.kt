package com.example.chatapp.domain.model

class User (
    val uid: String = "",
    val displayName: String = "",
    val photoUrl: String = "",
    val email: String = "",
    val fcmToken: String? = null, //FCM token for push notifications
    val createdAt: Long = System.currentTimeMillis()
)
