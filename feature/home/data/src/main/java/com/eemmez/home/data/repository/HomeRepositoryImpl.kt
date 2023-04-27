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
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService,
    private val favouriteDatabase: FavouriteDatabase
) : HomeRepository {
    override suspend fun getList(
        pageNumber: Int?,
        search: String?
    ): Result<GetListResponseEntity, HomeError> {
        val response = homeService.getList(pageNumber, search)
        return when (response.status) {
            Status.SUCCESS -> Result.Success(response.result?.toListResponseEntity())
            Status.FAIL -> Result.Error(HomeErrorMapper.map(response.errorCode))
        }
    }

    override suspend fun addToFavourites(listItemEntity: ListItemEntity): Result<Unit, HomeError> {
        favouriteDatabase.favouriteItemDao().insert(listItemEntity.toFavouriteItem())
        return Result.Success(Unit)
    }
}