package com.space.movieapp.domain.usecase.favorites.get_all

import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.domain.repository.FavoritesRepository
import com.space.movieapp.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

class GetAllFavoriteMoviesUseCase(private val repository: FavoritesRepository) :
    BaseUseCase<Unit, Flow<List<MoviesDomainModel.ResultDomain>>>() {
    override suspend fun invoke(params: Unit?): Flow<List<MoviesDomainModel.ResultDomain>> {
        return repository.getAllFavoriteMovies()
    }
}