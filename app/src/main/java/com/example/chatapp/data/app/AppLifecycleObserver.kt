package com.example.chatapp.data.app

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.chatapp.data.remote.OnlineStatusDataSource
import com.example.chatapp.domain.repository.AuthRepository
import com.example.chatapp.domain.repository.UpdateStatusRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppLifecycleObserver @Inject constructor(
    private val updateStatusRepository: UpdateStatusRepository,
    private val auth: FirebaseAuth
): DefaultLifecycleObserver {

    init {
        auth.addAuthStateListener{
            it.currentUser?.uid?.let {
                updateStatusRepository.updateOnlineStatus(it)
            }
        }
    }
}