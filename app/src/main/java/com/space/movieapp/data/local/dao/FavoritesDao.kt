package com.space.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.space.movieapp.data.local.entity.FavoritesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {
    @Insert
    suspend fun insertFavoriteMovie(movie: FavoritesEntity)

    @Query("DELETE FROM favorites WHERE id = :movieId")
    suspend fun deleteFavoriteMovie(movieId: Int)

    @Query("SELECT * FROM favorites")
    fun getAllFavoriteMovies(): Flow<List<FavoritesEntity>>

    @Query("SELECT * FROM favorites WHERE id = :movieId LIMIT 1")
    suspend fun isMovieInFavorites(movieId: Int): FavoritesEntity?

}
