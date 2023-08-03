package com.space.movieapp.data.repository

import com.space.movieapp.data.local.dao.FavoritesDao
import com.space.movieapp.data.local.mapper.FavoritesEntityToDomainMapper
import com.space.movieapp.domain.mapper.FavoritesDomainToEntityMapper
import com.space.movieapp.domain.model.FavoritesDomainModel
import com.space.movieapp.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(
    private val favoriteMovieDao: FavoritesDao,
    private val favoritesDomainToEntityMapper: FavoritesDomainToEntityMapper,
    private val favoritesEntityToDomainMapper: FavoritesEntityToDomainMapper
) : FavoritesRepository {
    override fun getAllFavoriteMovies(): Flow<List<FavoritesDomainModel>> {
        return favoriteMovieDao.getAllFavoriteMovies()
            .map { entityList -> entityList.map { favoritesEntityToDomainMapper.invoke(it) } }
    }

    override suspend fun insertFavoriteMovie(movie: FavoritesDomainModel) {
        val entity = favoritesDomainToEntityMapper.invoke(movie)
        favoriteMovieDao.insertFavoriteMovie(entity)
    }

    override suspend fun deleteFavoriteMovie(movieId: Int) {
        favoriteMovieDao.deleteFavoriteMovie(movieId)
    }

    override suspend fun isMovieInFavorites(movieId: Int): Boolean {
        return favoriteMovieDao.isMovieInFavorites(movieId) != null
    }
}
