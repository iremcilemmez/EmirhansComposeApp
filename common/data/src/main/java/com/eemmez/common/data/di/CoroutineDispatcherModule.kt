package com.eemmez.common.data.di

import com.eemmez.common.domain.di.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoroutineDispatcherModule {

    @IoDispatcher
    @Provides
    @Singleton
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}