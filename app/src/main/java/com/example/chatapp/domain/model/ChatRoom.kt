package com.example.chatapp.domain.model

data class ChatRoom(
    val roomId: String = "",
    val type: String = "", //private or group
    val members: List<String> = emptyList(),//list of user id
    val lastMessage: LastMessage? = null,//show preview of last message
    val metadata: GroupMetadata? = null //just for group chat
)

data class LastMessage(
    val text: String = "",
    val senderId: String = "",
    val sendAt: Long = 0L,
)

data class GroupMetadata(
    val name: String = "",
    val avatar: String? = null,
    val adminId: String = "",
)
