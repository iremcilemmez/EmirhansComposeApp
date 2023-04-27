package com.eemmez.favourite.domain.repository

import com.eemmez.common.domain.entity.Result
import com.eemmez.favourite.domain.entity.FavouriteError
import com.eemmez.favourite.domain.entity.FavouriteItemEntity
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    suspend fun getAll(): Result<List<FavouriteItemEntity>, FavouriteError.GetFavouritesError>

    suspend fun delete(favouriteItemEntity: FavouriteItemEntity): Result<Unit, FavouriteError.DeleteError>
}