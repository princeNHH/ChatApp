package com.example.chatapp.data.app

import com.example.chatapp.data.remote.OnlineStatusDataSource
import com.example.chatapp.data.repository.UpdateStatusRepositoryImpl
import com.example.chatapp.domain.repository.UpdateStatusRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //provided a global scope for app
    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }

    @Provides
    fun provideUpdateStatusRepository(
        database: OnlineStatusDataSource,
        applicationScope: CoroutineScope
    ): UpdateStatusRepository {
        return UpdateStatusRepositoryImpl(database, applicationScope)
    }
}