package com.space.movieapp.data.repository

import com.space.movieapp.data.remote.model.GenresDto
import com.space.movieapp.data.remote.service.ServiceApi
import com.space.movieapp.domain.repository.GenresRepository

class GenresRepositoryImpl(private val serviceApi: ServiceApi) : GenresRepository {

    private var genres: List<GenresDto.Genre>? = null

    override suspend fun getGenres(): List<GenresDto.Genre> {
        if (genres == null) {
            genres = fetchGenres()
        }
        return genres ?: emptyList()
    }

    private suspend fun fetchGenres(): List<GenresDto.Genre> {
        val response = serviceApi.getMovieGenres()
        return if (response.isSuccessful) {
            response.body()?.genres ?: emptyList()
        } else {
            emptyList()
        }
    }
}