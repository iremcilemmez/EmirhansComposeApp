package com.eemmez.favourite.data.di

import com.eemmez.favourite.data.repository.FavouriteRepositoryImpl
import com.eemmez.favourite.data.service.FavouriteDatabase
import com.eemmez.favourite.domain.repository.FavouriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavouriteRepositoryModule {
    @Provides
    @Singleton
    fun provideFavouriteRepository(favouriteDatabase: FavouriteDatabase): FavouriteRepository =
        FavouriteRepositoryImpl(favouriteDatabase)
}