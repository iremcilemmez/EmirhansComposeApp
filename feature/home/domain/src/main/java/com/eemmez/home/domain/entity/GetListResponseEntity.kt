package com.eemmez.home.domain.entity

data class GetListResponseEntity(
    val currentPage: Int,
    val nextPageAvailable: Boolean,
    val list: List<ListItemEntity>
)
