package com.eemmez.navigation.mapper

import com.eemmez.home.domain.entity.ListItemEntity
import com.vngrs.detail.domain.entity.DetailEntity

fun ListItemEntity.toDetailEntity() =
    DetailEntity(
        name = name,
        imageURL = imageUrl
    )
