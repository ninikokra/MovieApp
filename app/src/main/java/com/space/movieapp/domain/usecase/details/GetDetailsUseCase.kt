package com.space.movieapp.domain.usecase.details

import com.space.movieapp.domain.model.DetailsDomainModel
import com.space.movieapp.domain.repository.DetailsRepository
import com.space.movieapp.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

class GetDetailsUseCase(private val detailsRepository: DetailsRepository) :
    BaseUseCase<Int, Flow<DetailsDomainModel>>() {
    override suspend fun invoke(params: Int?): Flow<DetailsDomainModel> {
        val movieId = params ?: throw IllegalArgumentException()
        return detailsRepository.getMovie(movieId)
    }
}