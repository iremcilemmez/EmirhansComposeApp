package com.eemmez.favourite.data.mapper

import com.eemmez.favourite.data.dto.FavouriteItem
import com.eemmez.favourite.domain.entity.FavouriteItemEntity

fun FavouriteItem.toFavouriteItemEntity() =
    FavouriteItemEntity(
        name = name,
        imageURL = imageURL
    )