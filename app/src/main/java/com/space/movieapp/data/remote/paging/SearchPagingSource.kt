package com.space.movieapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.space.movieapp.data.remote.mapper.MoviesDtoToDomainMapper
import com.space.movieapp.data.remote.service.ServiceApi
import com.space.movieapp.domain.model.MoviesDomainModel

class SearchPagingSource(
    private val serviceApi: ServiceApi,
    private val query: String
) : PagingSource<Int, MoviesDomainModel.ResultDomain>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesDomainModel.ResultDomain> {
        return try {
            val page = params.key ?: 1

            val response = serviceApi.searchMovies(query, page)
            val responseBody = response.body()!!

            if (response.isSuccessful) {
                val moviesDtoList = responseBody.results
                val moviesDomainList = moviesDtoList.map { MoviesDtoToDomainMapper()(it) }

                val totalPages = responseBody.totalPages

                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (page < totalPages) page + 1 else null

                LoadResult.Page(
                    data = moviesDomainList,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(Exception())
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MoviesDomainModel.ResultDomain>): Int? {
        return state.anchorPosition
    }
}