package com.space.movieapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.space.movieapp.data.remote.mapper.MoviesDtoToDomainMapper
import com.space.movieapp.data.remote.model.MoviesDto
import com.space.movieapp.data.remote.service.ServiceApi
import com.space.movieapp.domain.model.MoviesDomainModel

class MoviesPagingSource(
    private val serviceApi: ServiceApi,
    private val category: String,
    private val moviesDtoToDomainMapper: MoviesDtoToDomainMapper
) : PagingSource<Int, MoviesDomainModel.ResultDomain>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesDomainModel.ResultDomain> {
        return try {
            val page = params.key ?: 1
            val response = serviceApi.getMovies(category, page)
            if (response.isSuccessful) {
                val movieList = response.body()?.results?.map { resultDto: MoviesDto.ResultDto ->
                    moviesDtoToDomainMapper(resultDto)
                } ?: emptyList()
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (page < (response.body()?.totalPages ?: 0)) page + 1 else null
                LoadResult.Page(movieList, prevKey, nextKey)
            } else {
                LoadResult.Error(Throwable(response.message()))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MoviesDomainModel.ResultDomain>): Int? {
        return state.anchorPosition
    }
}