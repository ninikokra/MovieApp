package com.space.movieapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.space.movieapp.data.remote.mapper.MoviesDtoToDomainMapper
import com.space.movieapp.data.remote.model.GenresDto
import com.space.movieapp.data.remote.model.MoviesDto
import com.space.movieapp.data.remote.service.ServiceApi
import com.space.movieapp.domain.model.MoviesDomainModel

class MoviesPagingSource(
    private val serviceApi: ServiceApi,
    private val category: String,
    private val moviesDtoToDomainMapper: MoviesDtoToDomainMapper
) : PagingSource<Int, MoviesDomainModel.ResultDomain>() {

    private var genres: List<GenresDto.Genre> = emptyList()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesDomainModel.ResultDomain> {
        return try {
            if (genres.isEmpty()) {
                getGenres()
            }
            val page = params.key ?: 1
            val response = serviceApi.getMovies(category, page)
            if (response.isSuccessful) {
                val movieList = response.body()?.results?.map { resultDto: MoviesDto.ResultDto ->
                    val mappedMovie = moviesDtoToDomainMapper(resultDto)
                    val mappedGenres = resultDto.genreIds.mapNotNull { genreId ->
                        genres.find { it.id == genreId }?.name
                    }
                    mappedMovie.copy(genreIds = mappedGenres)
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