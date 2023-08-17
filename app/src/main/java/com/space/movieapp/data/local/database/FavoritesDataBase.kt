package com.space.movieapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.space.movieapp.data.local.dao.FavoritesDao
import com.space.movieapp.data.local.entity.FavoritesEntity
import com.space.movieapp.utils.ListTypeConverters

@Database(
    entities = [FavoritesEntity::class],
    version = 9,
)
/**
 *  I use ListTypeConverter to convert lists of favorites data types into
 *  format that room can store in dataBase
 */
@TypeConverters(ListTypeConverters::class)
abstract class FavoritesDataBase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}