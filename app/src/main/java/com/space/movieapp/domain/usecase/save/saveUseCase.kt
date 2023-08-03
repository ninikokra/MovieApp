package com.space.movieapp.domain.usecase.save

import com.space.movieapp.domain.model.FavoritesDomainModel
import com.space.movieapp.domain.repository.FavoritesRepository
import com.space.movieapp.domain.usecase.base.BaseUseCase

class SaveOrDeleteFavoriteMovieUseCase(
    private val favoritesRepository: FavoritesRepository
) : BaseUseCase<FavoritesDomainModel, Unit>() {
    override suspend fun invoke(params: FavoritesDomainModel?) {
        val existingFavorite = params?.let { favoritesRepository.isMovieInFavorites(it.id) }
        if (existingFavorite != null) {
            favoritesRepository.deleteFavoriteMovie(params.id)
        } else {
            if (params != null) {
                favoritesRepository.insertFavoriteMovie(params)
            }
            }
        }
    }
