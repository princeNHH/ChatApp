package com.example.chatapp.data.app

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@HiltAndroidApp
class ChatApp : Application(){
    @Inject lateinit var lifecycleObserver: AppLifecycleObserver
    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(lifecycleObserver)
    }
}