package com.eemmez.home.data.repository

import com.eemmez.common.data.dto.Status
import com.eemmez.common.domain.entity.Result
import com.eemmez.favourite.data.service.FavouriteDatabase
import com.eemmez.home.data.mapper.HomeErrorMapper
import com.eemmez.home.data.mapper.toFavouriteItem
import com.eemmez.home.data.mapper.toListResponseEntity
import com.eemmez.home.data.service.HomeService
import com.eemmez.home.domain.entity.GetListResponseEntity
import com.eemmez.home.domain.entity.HomeError
import com.eemmez.home.domain.entity.ListItemEntity
import com.eemmez.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService,
    private val favouriteDatabase: FavouriteDatabase
) : HomeRepository {
    override fun getList(
        pageNumber: Int?,
        search: String?
    ): Flow<Result<GetListResponseEntity, HomeError>> =
        flow<Result<GetListResponseEntity, HomeError>> {
            val response = homeService.getList(pageNumber, search)
            when (response.status) {
                Status.SUCCESS -> emit(Result.Success(response.result?.toListResponseEntity()))
                Status.FAIL -> emit(Result.Error(HomeErrorMapper.map(response.errorCode)))
            }
        }.onStart {
            emit(Result.Loading())
        }.catch {
            emit(Result.Error(HomeError.Unknown))
        }

    override fun addToFavourites(listItemEntity: ListItemEntity): Flow<Result<Unit, HomeError>> =
        flow<Result<Unit, HomeError>> {
            favouriteDatabase.favouriteItemDao().insert(listItemEntity.toFavouriteItem())
            emit(Result.Success(Unit))
        }.onStart {
            emit(Result.Loading())
        }.catch {
            emit(Result.Error(HomeError.AddToFavouritesError))
        }
}