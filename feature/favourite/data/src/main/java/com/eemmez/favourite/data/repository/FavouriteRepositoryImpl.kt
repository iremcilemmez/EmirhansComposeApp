package com.eemmez.favourite.data.repository

import com.eemmez.common.domain.entity.Result
import com.eemmez.favourite.data.mapper.toFavouriteItem
import com.eemmez.favourite.data.mapper.toFavouriteItemEntity
import com.eemmez.favourite.data.service.FavouriteDatabase
import com.eemmez.favourite.domain.entity.FavouriteError
import com.eemmez.favourite.domain.entity.FavouriteItemEntity
import com.eemmez.favourite.domain.repository.FavouriteRepository
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val favouriteDatabase: FavouriteDatabase
) : FavouriteRepository {
    override suspend fun getAll(): Result<List<FavouriteItemEntity>, FavouriteError.GetFavouritesError> {
        val response = favouriteDatabase.favouriteItemDao().getAll()
        return Result.Success(response.map { it.toFavouriteItemEntity() })
    }

    override suspend fun delete(favouriteItemEntity: FavouriteItemEntity): Result<Unit, FavouriteError.DeleteError> {
        val favouriteItem = favouriteItemEntity.toFavouriteItem()
        favouriteDatabase.favouriteItemDao().delete(
            name = favouriteItem.name,
            imageURL = favouriteItem.imageURL
        )
        return Result.Success(Unit)
    }
}