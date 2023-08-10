package com.space.movieapp.domain.repository

import com.space.movieapp.domain.model.DetailsDomainModel
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {
    suspend fun getMovie(movieId: Int): Flow<DetailsDomainModel>
}