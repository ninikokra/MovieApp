package com.space.movieapp.domain.usecase.favorites.check_favorites

import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.domain.repository.FavoritesRepository
import com.space.movieapp.domain.usecase.base.BaseUseCase

class CheckFavoritesUseCase(private val repository: FavoritesRepository) :
    BaseUseCase<MoviesDomainModel.ResultDomain, Boolean>() {
    override suspend fun invoke(params: MoviesDomainModel.ResultDomain?): Boolean {
        if (params == null) {
            throw IllegalArgumentException()
        }
        return repository.checkFavoriteMovies(params.id)
    }
}