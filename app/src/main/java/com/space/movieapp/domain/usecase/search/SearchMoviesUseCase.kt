package com.space.movieapp.domain.usecase.search

import androidx.paging.PagingData
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.domain.repository.MoviesRepository
import com.space.movieapp.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

class SearchMoviesUseCase(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<String, Flow<PagingData<MoviesDomainModel.ResultDomain>>>() {
    override suspend fun invoke(params: String?): Flow<PagingData<MoviesDomainModel.ResultDomain>> {
        if (params == null) throw IllegalArgumentException()
        return moviesRepository.searchMovies(params)
    }
}