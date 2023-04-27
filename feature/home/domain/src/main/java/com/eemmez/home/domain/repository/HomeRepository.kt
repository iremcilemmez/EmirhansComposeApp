package com.eemmez.home.domain.repository

import com.eemmez.common.domain.entity.Result
import com.eemmez.home.domain.entity.GetListResponseEntity
import com.eemmez.home.domain.entity.HomeError
import com.eemmez.home.domain.entity.ListItemEntity
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getList(pageNumber: Int?, search: String?): Result<GetListResponseEntity, HomeError>
    suspend fun addToFavourites(listItemEntity: ListItemEntity): Result<Unit, HomeError>
}