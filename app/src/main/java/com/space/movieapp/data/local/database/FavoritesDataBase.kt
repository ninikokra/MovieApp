package com.space.movieapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.movieapp.data.local.dao.FavoritesDao
import com.space.movieapp.data.local.entity.FavoritesEntity

@Database(
    entities = [FavoritesEntity::class],
    version = 2,
)
abstract class FavoritesDataBase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}