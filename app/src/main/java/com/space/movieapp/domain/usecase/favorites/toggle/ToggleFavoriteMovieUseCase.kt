package com.space.movieapp.domain.usecase.favorites.toggle

import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.domain.repository.FavoritesRepository
import com.space.movieapp.domain.usecase.base.BaseUseCase

class ToggleFavoriteMovieUseCase(private val repository: FavoritesRepository) :
    BaseUseCase<MoviesDomainModel.ResultDomain, Unit>() {

    override suspend fun invoke(params: MoviesDomainModel.ResultDomain?) {
        params?.let { movie ->
            val isFavorite = repository.checkFavoriteMovies(movie.id)
            if (isFavorite) {
                repository.deleteFavoriteMovie(movie.id)
            } else {
                repository.insertFavoriteMovie(movie)
            }
        }
    }
}