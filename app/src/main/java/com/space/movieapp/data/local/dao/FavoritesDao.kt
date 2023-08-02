package com.space.movieapp.data.local.dao

import androidx.room.*
import com.space.movieapp.data.local.entity.FavoritesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movie: FavoritesEntity)

    @Delete
    suspend fun deleteFavoriteMovie(movie: FavoritesEntity)

    @Query("SELECT * FROM favorites")
    fun getAllFavoriteMovies(): Flow<List<FavoritesEntity>>

    @Query("SELECT * FROM favorites WHERE id = :movieId LIMIT 1")
    suspend fun isMovieInFavorites(movieId: Int): FavoritesEntity?
}
