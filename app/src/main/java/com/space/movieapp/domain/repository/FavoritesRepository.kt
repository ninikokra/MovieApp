package com.space.movieapp.domain.repository

import com.space.movieapp.data.local.entity.FavoritesEntity
import com.space.movieapp.domain.model.FavoritesDomainModel
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getAllFavoriteMovies(): Flow<List<FavoritesDomainModel>>
    suspend fun insertFavoriteMovie(movie: FavoritesDomainModel)
    suspend fun deleteFavoriteMovie(movieId: Int)
    suspend fun isMovieInFavorites(movieId: Int): Boolean
}