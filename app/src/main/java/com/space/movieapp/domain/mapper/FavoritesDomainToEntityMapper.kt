package com.space.movieapp.domain.mapper

import com.space.movieapp.data.local.entity.FavoritesEntity
import com.space.movieapp.domain.model.FavoritesDomainModel
import com.space.movieapp.utils.BaseMapper

class FavoritesDomainToEntityMapper : BaseMapper<FavoritesDomainModel, FavoritesEntity> {
    override fun invoke(domainModel: FavoritesDomainModel): FavoritesEntity {
        with(domainModel) {
            return FavoritesEntity(
                id = id,
                genreIds = genreIds,
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