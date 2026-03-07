package com.example.chatapp.domain.model

data class UsePresence(
    val state: String = "offline",
    val lastChange: Long = 0 //ServerValue.TIMESTAMP
)
