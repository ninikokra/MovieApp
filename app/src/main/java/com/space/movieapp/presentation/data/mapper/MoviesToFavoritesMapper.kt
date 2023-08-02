package com.space.movieapp.presentation.data.mapper

import com.space.movieapp.data.local.entity.FavoritesEntity
import com.space.movieapp.presentation.data.model.MoviesUIModel
import com.space.movieapp.utils.BaseMapper

class MoviesToFavoritesMapper : BaseMapper<MoviesUIModel.ResultUI, FavoritesEntity> {
    override fun invoke(model: MoviesUIModel.ResultUI): FavoritesEntity {
        with(model) {
            return FavoritesEntity(
                id = id,
                title = title,
                genres = genreIds.toString(),
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
