package com.space.movieapp.data.remote.mapper

import com.space.movieapp.data.remote.model.MoviesDto
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.utils.BaseMapper

class MoviesDtoToDomainMapper :
    BaseMapper<MoviesDto.ResultDto, MoviesDomainModel.ResultDomain> {
    override fun invoke(model: MoviesDto.ResultDto): MoviesDomainModel.ResultDomain {
        with(model) {
            return MoviesDomainModel.ResultDomain(
                id = id,
                title = title,
                adult = adult,
                backdropPath = backdropPath,
                genreIds = genreIds,
                originalLanguage = originalLanguage,
                originalTitle = originalTitle,
                overview = overview,
                popularity = popularity,
                posterPath = posterPath,
                releaseDate = releaseDate,
                video = video,
                voteAverage = voteAverage,
                voteCount = voteCount
            )
        }
    }
}