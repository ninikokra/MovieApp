package com.space.movieapp.domain.usecase.get_favorites_usecase

import com.space.movieapp.data.local.mapper.FavoritesEntityToDomainMapper
import com.space.movieapp.domain.model.FavoritesDomainModel
import com.space.movieapp.domain.repository.FavoritesRepository
import com.space.movieapp.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoriteMoviesUseCase(
    private val repository: FavoritesRepository
) : BaseUseCase<Unit, Flow<List<FavoritesDomainModel>>>() {

    override suspend fun invoke(params: Unit?): Flow<List<FavoritesDomainModel>> {
        return repository.getAllFavoriteMovies()
    }
}