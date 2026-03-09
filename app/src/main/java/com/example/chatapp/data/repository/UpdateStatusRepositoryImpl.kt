package com.example.chatapp.data.repository

import com.example.chatapp.data.remote.OnlineStatusDataSource
import com.example.chatapp.domain.repository.UpdateStatusRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateStatusRepositoryImpl @Inject constructor(
    private val database: OnlineStatusDataSource,
    private val applicationScope: CoroutineScope
) : UpdateStatusRepository {
    override fun updateOnlineStatus(uid: String) {
        applicationScope.launch {
            database.updateOnlineStatus(uid)
        }
    }
}