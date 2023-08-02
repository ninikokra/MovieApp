package com.space.movieapp.domain.usecase.toggle_favorites_usecase

import com.space.movieapp.data.local.entity.FavoritesEntity
import com.space.movieapp.domain.repository.FavoritesRepository
import com.space.movieapp.domain.usecase.base.BaseUseCase

class ToggleFavoriteMovieUseCase(private val repository: FavoritesRepository) : BaseUseCase<FavoritesEntity, Unit>() {
    override suspend fun invoke(params: FavoritesEntity?) {
        params?.let { movie ->
            val isFavorite = repository.isMovieInFavorites(movie.id)
            if (isFavorite) {
                repository.deleteFavoriteMovie(movie)
            } else {
                repository.insertFavoriteMovie(movie)
            }
        }
    }
}