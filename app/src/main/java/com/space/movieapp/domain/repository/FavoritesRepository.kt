package com.space.movieapp.domain.repository
import com.space.movieapp.domain.model.MoviesDomainModel
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getAllFavoriteMovies(): Flow<List<MoviesDomainModel.ResultDomain>>
    suspend fun insertFavoriteMovie(movie: MoviesDomainModel.ResultDomain)
    suspend fun deleteFavoriteMovie(movieId: Int)
    suspend fun checkFavoriteMovies(movieId: Int): Boolean
}