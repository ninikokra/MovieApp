package com.space.movieapp.data.local.mapper

import com.space.movieapp.data.local.entity.FavoritesEntity
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.utils.BaseMapper

class FavoritesEntityToDomainMapper : BaseMapper<FavoritesEntity, MoviesDomainModel.ResultDomain> {
    override fun invoke(model: FavoritesEntity): MoviesDomainModel.ResultDomain{
        return with(model) {
            MoviesDomainModel.ResultDomain(
                id = id,
                title = title,
                genreIds = genreIds,
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