package com.space.movieapp.presentation.data.mapper

import com.space.movieapp.data.local.entity.FavoritesEntity
import com.space.movieapp.presentation.data.model.MoviesUIModel
import com.space.movieapp.utils.BaseMapper

class FavoritesToMoviesMapper : BaseMapper<FavoritesEntity, MoviesUIModel.ResultUI> {
    override fun invoke(entity: FavoritesEntity): MoviesUIModel.ResultUI {
        return with(entity) {
            MoviesUIModel.ResultUI(
                id = id,
                title = title,
                genreIds = genres.split(",").map { it.trim() },
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
