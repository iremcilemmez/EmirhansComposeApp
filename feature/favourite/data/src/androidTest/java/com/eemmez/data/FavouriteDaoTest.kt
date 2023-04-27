package com.eemmez.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eemmez.favourite.data.dao.FavouriteItemDao
import com.eemmez.favourite.data.dto.FavouriteItem
import com.eemmez.favourite.data.service.FavouriteDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavouriteDaoTest {
    private lateinit var favouriteDatabase: FavouriteDatabase
    private lateinit var favouriteItemDao: FavouriteItemDao

    @Before
    fun setup() {
        favouriteDatabase = Room.inMemoryDatabaseBuilder(
            context = ApplicationProvider.getApplicationContext(),
            klass = FavouriteDatabase::class.java
        ).allowMainThreadQueries().build()

        favouriteItemDao = favouriteDatabase.favouriteItemDao()
    }

    @After
    fun tearDown() {
        favouriteDatabase.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertItem() {
        runTest {
            val favouriteItem = FavouriteItem(id = 0, name = "test", imageURL = "image")
            favouriteItemDao.insert(favouriteItem)

            val favouriteList = favouriteItemDao.getAll()
            assert(favouriteList.contains(favouriteItem))
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteItem() {
        runTest {
            val favouriteItem = FavouriteItem(name = "test", imageURL = "image")
            favouriteItemDao.insert(favouriteItem)
            favouriteItemDao.delete("test", "image")

            val favouriteList = favouriteItemDao.getAll()
            assert(favouriteList.contains(favouriteItem).not())
        }
    }
}