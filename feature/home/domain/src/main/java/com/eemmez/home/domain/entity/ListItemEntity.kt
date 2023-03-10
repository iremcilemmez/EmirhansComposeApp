package com.eemmez.home.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListItemEntity(
    val name: String,
    val imageUrl: String
) : Parcelable