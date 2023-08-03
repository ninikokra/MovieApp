package com.space.movieapp.domain.usecase.toggle_favorites_usecase

import com.space.movieapp.domain.model.FavoritesDomainModel
import com.space.movieapp.domain.repository.FavoritesRepository
import com.space.movieapp.domain.usecase.base.BaseUseCase

class ToggleFavoriteMovieUseCase(private val repository: FavoritesRepository) :
    BaseUseCase<FavoritesDomainModel, Unit>() {

    override suspend fun invoke(params: FavoritesDomainModel?) {
        params?.let { movie ->
            val isFavorite = repository.isMovieInFavorites(movie.id)
            if (isFavorite) {
                repository.deleteFavoriteMovie(movie.id)
            } else {
                repository.insertFavoriteMovie(movie)
            }
        }
    }
}