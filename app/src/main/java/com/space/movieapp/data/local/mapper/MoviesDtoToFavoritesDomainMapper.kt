package com.space.movieapp.data.local.mapper

import com.space.movieapp.data.remote.model.MoviesDto
import com.space.movieapp.domain.model.FavoritesDomainModel
import com.space.movieapp.utils.BaseMapper

class MoviesDtoToFavoritesDomainMapper : BaseMapper<MoviesDto.ResultDto, FavoritesDomainModel> {
    override fun invoke(dto: MoviesDto.ResultDto): FavoritesDomainModel {
        return with(dto) {
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