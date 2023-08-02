package com.space.movieapp.data.repository

import com.space.movieapp.data.local.dao.FavoritesDao
import com.space.movieapp.data.local.entity.FavoritesEntity
import com.space.movieapp.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class FavoritesRepositoryImpl(private val favoriteMovieDao: FavoritesDao) : FavoritesRepository {
    override fun getAllFavoriteMovies(): Flow<List<FavoritesEntity>> {
        return favoriteMovieDao.getAllFavoriteMovies()
    }

    override suspend fun insertFavoriteMovie(movie: FavoritesEntity) {
        favoriteMovieDao.insertFavoriteMovie(movie)
    }

    override suspend fun deleteFavoriteMovie(movie: FavoritesEntity) {
        favoriteMovieDao.deleteFavoriteMovie(movie)
    }

    override suspend fun isMovieInFavorites(movieId: Int): Boolean {
        return favoriteMovieDao.isMovieInFavorites(movieId) != null
    }
}