package com.example.chatapp.di

import com.example.chatapp.data.remote.AuthRemoteDataSource
import com.example.chatapp.data.remote.ChatFirestoreDataSource
import com.example.chatapp.data.remote.ChatStorageDataSource
import com.example.chatapp.data.remote.OnlineStatusDataSource
import com.example.chatapp.data.repository.AuthRepositoryImpl
import com.example.chatapp.data.repository.ChatRepositoryImpl
import com.example.chatapp.domain.repository.AuthRepository
import com.example.chatapp.domain.repository.ChatRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ChatModule {
    @Provides
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    fun provideStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Provides
    fun provideRealtime(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    fun provideChatRepository(
        firestore: ChatFirestoreDataSource,
        storage: ChatStorageDataSource,
        realtime: OnlineStatusDataSource
    ): ChatRepository {
        return ChatRepositoryImpl(firestore, storage, realtime)
    }

    @Provides
    fun provideAuthRepository(remote: AuthRemoteDataSource): AuthRepository {
        return AuthRepositoryImpl(remote)
    }
}