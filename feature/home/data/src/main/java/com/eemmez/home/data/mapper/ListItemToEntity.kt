package com.eemmez.home.data.mapper

import com.eemmez.home.data.dto.ListItem
import com.eemmez.home.domain.entity.ListItemEntity

fun ListItem.toListItemEntity(): ListItemEntity =
    ListItemEntity(
        name = name,
        imageURL = imageUrl
    )