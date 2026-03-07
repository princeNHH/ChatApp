package com.example.chatapp.domain.model

data class Message(
    val id: String = "",
    val chatId: String = "",
    val senderId: String = "",
    val message: String = "",
    val timestamp: Long = 0
)
