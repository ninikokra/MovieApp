package com.space.movieapp.domain.model.mapper

import com.space.movieapp.domain.model.DetailsDomainModel
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.utils.BaseMapper

class DetailsToMoviesDomainMapper: BaseMapper<DetailsDomainModel,MoviesDomainModel.ResultDomain> {
    override fun invoke(model: DetailsDomainModel): MoviesDomainModel.ResultDomain {
        with(model){
            return MoviesDomainModel.ResultDomain(
                id = id,
                title = originalTitle,
                genreIds = genres,
                releaseDate = releaseDate,
                posterPath = posterPath,
                backdropPath = backdropPath,
                popularity = popularity,
                voteAverage = voteAverage,
                voteCount = voteCount,
                adult = false,
                originalLanguage = "",
                video = false,
                originalTitle = originalTitle,
                overview = ""
            )
        }

    }
}
