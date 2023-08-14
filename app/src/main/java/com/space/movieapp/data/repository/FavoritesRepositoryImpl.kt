package com.space.movieapp.data.repository

import com.space.movieapp.data.local.dao.FavoritesDao
import com.space.movieapp.data.local.mapper.FavoritesEntityToDomainMapper
import com.space.movieapp.data.local.mapper.MoviesDomainToFavoritesEntityMapper
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(
    private val favoritesDao: FavoritesDao,
    private val favoritesDomainToEntityMapper: MoviesDomainToFavoritesEntityMapper,
    private val favoritesEntityToDomainMapper: FavoritesEntityToDomainMapper
) : FavoritesRepository {
    override fun getAllFavoriteMovies(): Flow<List<MoviesDomainModel.ResultDomain>> {
        return favoritesDao.getAllFavoriteMovies()
            .map { entities -> entities.map { favoritesEntityToDomainMapper.invoke(it) } }
    }

    override suspend fun insertFavoriteMovie(movie: MoviesDomainModel.ResultDomain) {
        val entity = favoritesDomainToEntityMapper.invoke(movie)
        favoritesDao.insertFavoriteMovie(entity)
    }

    override suspend fun deleteFavoriteMovie(movieId: Int) {
        favoritesDao.deleteFavoriteMovie(movieId)
    }

    override suspend fun checkFavoriteMovies(movieId: Int): Boolean {
        return favoritesDao.isMovieInFavorites(movieId)
    }
}