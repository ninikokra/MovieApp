package com.space.movieapp.domain.repository

import com.space.movieapp.data.local.entity.FavoritesEntity
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getAllFavoriteMovies(): Flow<List<FavoritesEntity>>
    suspend fun insertFavoriteMovie(movie: FavoritesEntity)
    suspend fun deleteFavoriteMovie(movie: FavoritesEntity)
    suspend fun isMovieInFavorites(movieId: Int): Boolean
}
