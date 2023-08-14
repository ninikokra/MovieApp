package com.space.movieapp.di

import android.content.Context
import androidx.room.Room
import com.space.movieapp.data.local.database.FavoritesDataBase
import org.koin.dsl.module

private fun provideDataBase(context: Context): FavoritesDataBase{
    return Room.databaseBuilder(context, FavoritesDataBase::class.java,"favorites")
        .fallbackToDestructiveMigration().build()
}
private fun provideDao(db: FavoritesDataBase) = db.favoritesDao()

val dataBaseModule = module {
    single { provideDao(get()) }
    single { provideDataBase(get()) }
}
