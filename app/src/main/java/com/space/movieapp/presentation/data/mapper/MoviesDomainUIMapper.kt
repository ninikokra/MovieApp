package com.space.movieapp.presentation.data.mapper

import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.presentation.data.model.MoviesUIModel
import com.space.movieapp.utils.BaseMapper

class MoviesDomainUIMapper : BaseMapper<MoviesDomainModel.ResultDomain, MoviesUIModel.ResultUI> {
    override fun invoke(model: MoviesDomainModel.ResultDomain): MoviesUIModel.ResultUI {
        with(model){
            return MoviesUIModel.ResultUI(
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