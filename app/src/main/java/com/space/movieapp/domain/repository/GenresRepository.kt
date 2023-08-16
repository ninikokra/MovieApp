package com.space.movieapp.domain.repository

import com.space.movieapp.data.remote.model.GenresDto

interface GenresRepository {
    suspend fun getGenres(): List<GenresDto.Genre>?
}