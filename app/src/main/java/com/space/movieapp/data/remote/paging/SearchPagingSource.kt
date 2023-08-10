package com.space.movieapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.space.movieapp.data.remote.mapper.MoviesDtoToDomainMapper
import com.space.movieapp.data.remote.model.GenresDto
import com.space.movieapp.data.remote.model.MoviesDto
import com.space.movieapp.data.remote.service.ServiceApi
import com.space.movieapp.domain.model.MoviesDomainModel

class SearchPagingSource(
    private val serviceApi: ServiceApi,
    private val query: String,
    private val moviesDtoToDomainMapper:MoviesDtoToDomainMapper
) : PagingSource<Int, MoviesDomainModel.ResultDomain>() {

    private var genres: List<GenresDto.Genre> = emptyList()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesDomainModel.ResultDomain> {
        return try {
            if (genres.isEmpty()) {
                getGenres()
            }
            val page = params.key ?: 1

            val response = serviceApi.searchMovies(query, page)
            val responseBody = response.body()!!

            if (response.isSuccessful) {
                val searchList = response.body()?.results?.map { resultDto: MoviesDto.ResultDto ->
                    val mappedSearch = moviesDtoToDomainMapper(resultDto)
                    val mappedGenres = resultDto.genreIds.mapNotNull { genresId->
                        genres.find { it.id == genresId }?.name
                    }
                    mappedSearch.copy(genreIds = mappedGenres)

                }?: emptyList()

                val totalPages = responseBody.totalPages


                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (page < totalPages) page + 1 else null

                LoadResult.Page(
                    data = searchList,
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
    private suspend fun getGenres() {
        val response = serviceApi.getMovieGenres()
        if (response.isSuccessful) {
            genres = response.body()?.genres ?: emptyList()
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MoviesDomainModel.ResultDomain>): Int? {
        return state.anchorPosition
    }
}