package com.eemmez.home.data.mapper

import com.eemmez.favourite.data.dto.FavouriteItem
import com.eemmez.home.domain.entity.ListItemEntity

fun ListItemEntity.toFavouriteItem(): FavouriteItem =
    FavouriteItem(
        name = name,
        imageUrl = imageUrl
    )