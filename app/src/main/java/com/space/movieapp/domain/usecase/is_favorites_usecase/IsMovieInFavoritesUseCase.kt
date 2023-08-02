package com.space.movieapp.domain.usecase.is_favorites_usecase

import com.space.movieapp.domain.repository.FavoritesRepository
import com.space.movieapp.domain.usecase.base.BaseUseCase

class IsMovieInFavoritesUseCase(private val repository: FavoritesRepository) : BaseUseCase<Int, Boolean>() {
    override suspend fun invoke(params: Int?): Boolean {
        return params?.let { movieId ->
            repository.isMovieInFavorites(movieId)
        } ?: false
    }
}