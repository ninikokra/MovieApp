package com.space.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.space.movieapp.data.remote.mapper.MoviesDtoToDomainMapper
import com.space.movieapp.data.remote.paging.MoviesPagingSource
import com.space.movieapp.data.remote.paging.SearchPagingSource
import com.space.movieapp.data.remote.service.ServiceApi
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class MoviesRepositoryImpl(
    private val serviceApi: ServiceApi,
    private val moviesDtoToDomainMapper: MoviesDtoToDomainMapper
) : MoviesRepository {
    override fun getMovies(category: String, page: Int): Flow<PagingData<MoviesDomainModel.ResultDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesPagingSource(serviceApi, category,moviesDtoToDomainMapper) }
        ).flow
    }

    override suspend fun searchMovies(query: String): Flow<PagingData<MoviesDomainModel.ResultDomain>> {
            return Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { SearchPagingSource(serviceApi, query) }
            ).flow
    }
}