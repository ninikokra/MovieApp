package com.space.movieapp.domain.usecase.getMovies

import com.space.movieapp.domain.repository.MoviesRepository
import com.space.movieapp.domain.usecase.base.BaseUseCase
import androidx.paging.PagingData
import com.space.movieapp.domain.model.MoviesDomainModel
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(
    private val moviesRepository: MoviesRepository
) : BaseUseCase<GetMoviesUseCase.Params, Flow<PagingData<MoviesDomainModel.ResultDomain>>>() {

    //todo
    data class Params(
        val category: String,
        val page: Int,
    )

    override suspend fun invoke(params: Params?): Flow<PagingData<MoviesDomainModel.ResultDomain>> {
        if (params == null) {
            throw IllegalArgumentException()
        }
        val (category, page) = params
        return moviesRepository.getMovies(category, page)
    }
}