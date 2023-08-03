package com.space.movieapp.presentation.data.mapper

import com.space.movieapp.domain.model.FavoritesDomainModel
import com.space.movieapp.presentation.data.model.MoviesUIModel
import com.space.movieapp.utils.BaseMapper

class FavoritesDomainToMoviesUIMapper : BaseMapper<FavoritesDomainModel, MoviesUIModel.ResultUI> {
    override fun invoke(model: FavoritesDomainModel): MoviesUIModel.ResultUI {
        return with(model) {
            MoviesUIModel.ResultUI(
                id = id,
                title = title,
                genreIds = genreIds.split(",").map { it.trim() },
                voteAverage = voteAverage,
                releaseDate = releaseDate,
                posterPath = posterPath,
                adult = adult,
                backdropPath = backdropPath,
                originalLanguage = originalLanguage,
                originalTitle = originalTitle,
                overview = overview,
                popularity = popularity,
                video = video,
                voteCount = voteCount
            )
        }
    }
}
