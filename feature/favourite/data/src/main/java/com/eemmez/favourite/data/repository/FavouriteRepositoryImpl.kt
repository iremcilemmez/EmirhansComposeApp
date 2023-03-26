package com.eemmez.favourite.data.repository

import android.util.Log
import com.eemmez.common.domain.entity.Result
import com.eemmez.favourite.data.mapper.toFavouriteItem
import com.eemmez.favourite.data.mapper.toFavouriteItemEntity
import com.eemmez.favourite.data.service.FavouriteDatabase
import com.eemmez.favourite.domain.entity.FavouriteError
import com.eemmez.favourite.domain.entity.FavouriteItemEntity
import com.eemmez.favourite.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val favouriteDatabase: FavouriteDatabase
) : FavouriteRepository {
    override fun getAll(): Flow<Result<List<FavouriteItemEntity>, FavouriteError.GetFavouritesError>> =
        flow<Result<List<FavouriteItemEntity>, FavouriteError.GetFavouritesError>> {
            val response = favouriteDatabase.favouriteItemDao().getAll()
            emit(Result.Success(response.map { it.toFavouriteItemEntity() }))
        }.onStart {
            emit(Result.Loading())
        }.catch {
            emit(Result.Error(FavouriteError.GetFavouritesError))
        }

    override fun delete(favouriteItemEntity: FavouriteItemEntity): Flow<Result<Unit, FavouriteError.DeleteError>> =
        flow<Result<Unit, FavouriteError.DeleteError>> {
            val favouriteItem = favouriteItemEntity.toFavouriteItem()
            favouriteDatabase.favouriteItemDao().delete(
                name = favouriteItem.name,
                imageURL = favouriteItem.imageURL
            )
            emit(Result.Success(Unit))
        }.onStart {
            emit(Result.Loading())
        }.catch {
            Log.e("Error", it.message.toString())
            emit(Result.Error(FavouriteError.DeleteError))
        }
}