package com.example.chatapp.di

import com.example.chatapp.data.remote.AuthRemoteDataSource
import com.example.chatapp.data.remote.ChatRemoteDataSource
import com.example.chatapp.data.repository.AuthRepositoryImpl
import com.example.chatapp.data.repository.ChatRepositoryImpl
import com.example.chatapp.domain.repository.AuthRepository
import com.example.chatapp.domain.repository.ChatRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun provideChatRepository(remote: ChatRemoteDataSource): ChatRepository {
        return ChatRepositoryImpl(remote)
    }

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    fun provideAuthRepository(remote: AuthRemoteDataSource): AuthRepository {
        return AuthRepositoryImpl(remote)
    }
}