package com.example.chatapp.domain.repository

interface UpdateStatusRepository {
    fun updateOnlineStatus(uid: String)
}