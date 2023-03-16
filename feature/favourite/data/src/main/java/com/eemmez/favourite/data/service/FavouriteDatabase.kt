package com.eemmez.favourite.data.service

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eemmez.favourite.data.dao.FavouriteItemDao
import com.eemmez.favourite.data.dto.FavouriteItem

@Database(entities = [FavouriteItem::class], version = 1, exportSchema = false)
abstract class FavouriteDatabase : RoomDatabase() {
    abstract fun favouriteItemDao(): FavouriteItemDao
}