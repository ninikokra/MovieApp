package com.space.movieapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.space.movieapp.data.remote.mapper.MoviesDtoToDomainMapper
import com.space.movieapp.data.remote.model.GenresDto
import com.space.movieapp.data.remote.model.MoviesDto
import com.space.movieapp.data.remote.service.ServiceApi
import com.space.movieapp.data.repository.MoviesRepositoryImpl
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.domain.repository.GenresRepository

class MoviesPagingSource(
    private val serviceApi: ServiceApi,
    private val category: String,
) : PagingSource<Int, MoviesDto.ResultDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesDto.ResultDto> {
        return try {
            val page = params.key ?: 1
            val response = serviceApi.getMovies(category, page)
            val responseBody = response.body()!!

            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (page < (responseBody.totalPages ?: 0)) page + 1 else null
            LoadResult.Page(responseBody.results, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MoviesDto.ResultDto>): Int? {
        return state.anchorPosition
    }
}