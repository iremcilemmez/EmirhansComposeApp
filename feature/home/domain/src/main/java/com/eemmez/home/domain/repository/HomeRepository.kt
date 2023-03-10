package com.eemmez.home.domain.repository

import com.eemmez.common.domain.entity.Result
import com.eemmez.home.domain.entity.GetListResponseEntity
import com.eemmez.home.domain.entity.HomeError
import com.eemmez.home.domain.entity.ListItemEntity
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getList(pageNumber: Int?, search: String?): Flow<Result<GetListResponseEntity, HomeError>>
    fun addToFavourites(listItemEntity: ListItemEntity): Flow<Result<Unit, HomeError>>
}