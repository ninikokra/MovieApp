package com.space.movieapp.data.repository

import com.space.movieapp.data.remote.mapper.DetailsDtoToDomainMapper
import com.space.movieapp.data.remote.model.DetailsDto
import com.space.movieapp.data.remote.service.ServiceApi
import com.space.movieapp.domain.model.DetailsDomainModel
import com.space.movieapp.domain.repository.DetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class DetailsRepositoryImpl(
    private val serviceApi: ServiceApi,
    private val detailsDtoToDomainMapper: DetailsDtoToDomainMapper
) : DetailsRepository {
    override suspend fun getMovie(movieId: Int): Flow<DetailsDomainModel> = flow {
        try {
            val response: Response<DetailsDto> = serviceApi.getMovieDetails(movieId)

            if (response.isSuccessful) {
                val detailsDto: DetailsDto? = response.body()
                if (detailsDto != null) {
                    val detailsDomainModel = detailsDtoToDomainMapper(detailsDto)
                    emit(detailsDomainModel)
                } else {
                    throw Exception("${response.code()}")
                }
            } else {
                throw Exception("${response.code()}")
            }
        } catch (e: Exception) {
            throw e
        }
    }
}