package com.eemmez.navigation.mapper

import com.eemmez.favourite.domain.entity.FavouriteItemEntity
import com.vngrs.detail.domain.entity.DetailEntity

fun FavouriteItemEntity.toDetailEntity() =
    DetailEntity(
        name = name,
        imageURL = imageURL
    )