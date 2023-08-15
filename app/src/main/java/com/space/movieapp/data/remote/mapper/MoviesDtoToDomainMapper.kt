package com.space.movieapp.data.remote.mapper

import com.space.movieapp.data.remote.model.MoviesDto
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.utils.BaseMapper

class MoviesDtoToDomainMapper :
    BaseMapper<MoviesDto.ResultDto, MoviesDomainModel.ResultDomain> {
    override fun invoke(model: MoviesDto.ResultDto): MoviesDomainModel.ResultDomain {
        with(model) {
            return MoviesDomainModel.ResultDomain(
                id = id ?: 0,
                title = title ?: "",
                adult = adult ?: false,
                backdropPath = backdropPath ?: "",
                genreIds = genreIds!!.map { it.toString() },
                originalLanguage = originalLanguage ?: "",
                originalTitle = originalTitle ?: "",
                overview = overview?: "",
                popularity = popularity ?: 0.0,
                posterPath = posterPath ?: "",
                releaseDate = releaseDate ?: "",
                video = video ?: false,
                voteAverage = voteAverage?: 0.0,
                voteCount = voteCount?: 0
            )
        }
    }
}