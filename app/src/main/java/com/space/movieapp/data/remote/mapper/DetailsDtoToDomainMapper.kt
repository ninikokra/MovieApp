package com.space.movieapp.data.remote.mapper

import com.space.movieapp.data.remote.model.DetailsDto
import com.space.movieapp.domain.model.DetailsDomainModel
import com.space.movieapp.utils.BaseMapper

class DetailsDtoToDomainMapper : BaseMapper<DetailsDto, DetailsDomainModel> {
    override fun invoke(model: DetailsDto): DetailsDomainModel {
        with(model) {
            return DetailsDomainModel(
                id = id ?: 0,
                genres = genres!!.map { it.name },
                originalTitle = originalTitle ?: "",
                overview = overview ?: "",
                runtime = runtime ?: 0,
                voteAverage = voteAverage ?: 0.0,
                posterPath = posterPath ?: "",
                releaseDate = releaseDate ?: "",
                backdropPath = backdropPath ?:"",
                voteCount = voteCount ?: 0,
                popularity = popularity ?: 0.0,
                isFavorite = isFavorite,
            )
        }
    }
}