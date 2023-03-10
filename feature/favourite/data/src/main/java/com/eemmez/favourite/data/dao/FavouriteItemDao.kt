package com.eemmez.favourite.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.eemmez.favourite.data.dto.FavouriteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteItemDao {
    @Query("SELECT * from FavouriteItem")
    suspend fun getAll(): List<FavouriteItem>

    @Insert
    suspend fun insert(favouriteItem: FavouriteItem)

    @Delete
    suspend fun delete(favouriteItem: FavouriteItem)
}