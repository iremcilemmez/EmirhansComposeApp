package com.eemmez.favourite.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteItem(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image_url") val imageUrl: String
)
