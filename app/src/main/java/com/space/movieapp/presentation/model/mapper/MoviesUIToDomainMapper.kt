package com.space.movieapp.presentation.model.mapper

import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.presentation.model.model.MoviesUIModel
import com.space.movieapp.utils.BaseMapper

class MoviesUIToDomainMapper : BaseMapper<MoviesUIModel.ResultUI, MoviesDomainModel.ResultDomain> {
    override fun invoke(model: MoviesUIModel.ResultUI): MoviesDomainModel.ResultDomain {
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
                voteCount = voteCount,
            )
        }
    }
}