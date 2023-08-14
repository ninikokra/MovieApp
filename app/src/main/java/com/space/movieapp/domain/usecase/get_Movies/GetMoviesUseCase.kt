package com.space.movieapp.domain.usecase.get_Movies

import com.space.movieapp.domain.repository.MoviesRepository
import com.space.movieapp.domain.usecase.base.BaseUseCase
import androidx.paging.PagingData
import com.space.movieapp.data.remote.model.RequestGetMoviesParams
import com.space.movieapp.domain.model.MoviesDomainModel
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<RequestGetMoviesParams, Flow<PagingData<MoviesDomainModel.ResultDomain>>>() {

    override suspend fun invoke(params: RequestGetMoviesParams?): Flow<PagingData<MoviesDomainModel.ResultDomain>> {
        if (params == null) {
            throw IllegalArgumentException()
        }
        return moviesRepository.getMovies(params.category, params.page)
    }
}