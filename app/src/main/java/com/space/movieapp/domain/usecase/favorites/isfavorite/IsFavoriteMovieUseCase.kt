package com.space.movieapp.domain.usecase.favorites.isfavorite

import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.domain.repository.FavoritesRepository
import com.space.movieapp.domain.usecase.base.BaseUseCase

class IsFavoriteMovieUseCase(private val repository: FavoritesRepository) :
    BaseUseCase<MoviesDomainModel.ResultDomain, Unit>() {
    override suspend fun invoke(params: MoviesDomainModel.ResultDomain?) {
        if (params == null) {
            throw IllegalArgumentException()
        }
        val isFavorite = repository.checkFavoriteMovies(params.id)
        if (isFavorite) {
            repository.deleteFavoriteMovie(params.id)
        } else {
            repository.insertFavoriteMovie(params)
        }
    }
}