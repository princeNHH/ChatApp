package com.example.chatapp.domain.model

data class Message(
    val id: String = "",
    val senderId: String = "",
    val text: String = "",
    val type: String = "text", //text, image, video, audio, file
    val mediaUrl: String = "", // url from firebase storage
    val timestamp: Long = System.currentTimeMillis(),
    val readBy: Map<String, Boolean> = emptyMap() // "uid1": true, "uid2": true
)
