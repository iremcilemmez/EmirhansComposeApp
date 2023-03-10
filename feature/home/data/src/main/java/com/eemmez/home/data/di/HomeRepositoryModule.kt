package com.eemmez.home.data.di

import com.eemmez.favourite.data.dao.FavouriteItemDao
import com.eemmez.favourite.data.service.FavouriteDatabase
import com.eemmez.home.data.repository.HomeRepositoryImpl
import com.eemmez.home.data.service.HomeService
import com.eemmez.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeRepositoryModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        homeService: HomeService,
        favouriteDatabase: FavouriteDatabase
    ): HomeRepository =
        HomeRepositoryImpl(homeService, favouriteDatabase)
}