package com.eemmez.home.data.mapper

import com.eemmez.home.data.dto.GetListResponse
import com.eemmez.home.domain.entity.GetListResponseEntity

fun GetListResponse.toListResponseEntity() =
    GetListResponseEntity(
        currentPage = currentPage,
        nextPageAvailable = nextPageAvailable,
        list = list.map { it.toListItemEntity() }
    )