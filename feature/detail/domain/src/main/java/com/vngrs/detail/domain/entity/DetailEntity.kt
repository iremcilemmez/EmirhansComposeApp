package com.vngrs.detail.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailEntity(
    val name: String,
    val imageURL: String
) : Parcelable
