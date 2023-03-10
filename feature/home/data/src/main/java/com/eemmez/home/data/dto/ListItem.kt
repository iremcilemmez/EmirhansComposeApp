package com.eemmez.home.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ListItem(
    val name: String,
    val imageUrl: String
)
