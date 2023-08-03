package com.space.movieapp.presentation.data.mapper

import com.space.movieapp.domain.model.FavoritesDomainModel
import com.space.movieapp.presentation.data.model.MoviesUIModel
import com.space.movieapp.utils.BaseMapper

class MoviesUIToFavoritesDomainMapper : BaseMapper<MoviesUIModel.ResultUI, FavoritesDomainModel> {
    override fun invoke(model: MoviesUIModel.ResultUI): FavoritesDomainModel {
        with(model) {
            return FavoritesDomainModel(
                id = id,
                genreIds = genreIds.toString(),
                title = title,
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