package com.space.movieapp.domain.usecase.get_favorites_usecase

import com.space.movieapp.data.local.entity.FavoritesEntity
import com.space.movieapp.domain.repository.FavoritesRepository
import com.space.movieapp.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

class GetFavoriteMoviesUseCase(private val repository: FavoritesRepository) :
    BaseUseCase<Unit, Flow<List<FavoritesEntity>>>() {
    override suspend fun invoke(params: Unit?): Flow<List<FavoritesEntity>> {
        return repository.getAllFavoriteMovies()
    }
}
