package com.space.movieapp.data.local.mapper

import com.space.movieapp.data.local.entity.FavoritesEntity
import com.space.movieapp.domain.model.FavoritesDomainModel
import com.space.movieapp.utils.BaseMapper

class FavoritesEntityToDomainMapper : BaseMapper<FavoritesEntity, FavoritesDomainModel> {
    override fun invoke(entity: FavoritesEntity): FavoritesDomainModel{
        return with(entity) {
            FavoritesDomainModel(
                id = id,
                title = title,
                genreIds = genreIds.toString(),
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