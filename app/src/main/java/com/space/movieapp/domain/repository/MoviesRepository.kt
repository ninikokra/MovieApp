package com.space.movieapp.domain.repository

import androidx.paging.PagingData
import com.space.movieapp.domain.model.MoviesDomainModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMovies(category: String, page: Int): Flow<PagingData<MoviesDomainModel.ResultDomain>>
    fun searchMovies(query: String):Flow<PagingData<MoviesDomainModel.ResultDomain>>
}