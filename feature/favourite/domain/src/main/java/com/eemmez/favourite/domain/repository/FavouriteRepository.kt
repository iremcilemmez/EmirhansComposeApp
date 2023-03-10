package com.eemmez.favourite.domain.repository

import com.eemmez.common.domain.entity.Result
import com.eemmez.favourite.domain.entity.FavouriteError
import com.eemmez.favourite.domain.entity.FavouriteItemEntity
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    fun getAll(): Flow<Result<List<FavouriteItemEntity>, FavouriteError.GetFavouritesError>>

    fun delete(favouriteItemEntity: FavouriteItemEntity): Flow<Result<Unit, FavouriteError.DeleteError>>
}