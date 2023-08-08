package com.space.movieapp.domain.usecase.favorites.delete

import com.space.movieapp.domain.repository.FavoritesRepository
import com.space.movieapp.domain.usecase.base.BaseUseCase

class DeleteFavoriteMovieUseCase(private val repository: FavoritesRepository) :
    BaseUseCase<Int, Unit>() {
    override suspend fun invoke(params: Int?) {
        params?.let { repository.deleteFavoriteMovie(it) }
    }
}