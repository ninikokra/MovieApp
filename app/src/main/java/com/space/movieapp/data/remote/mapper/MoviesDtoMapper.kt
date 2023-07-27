package com.space.movieapp.data.remote.mapper

import androidx.paging.PagingData
import com.space.movieapp.data.remote.model.MoviesDto
import com.space.movieapp.data.remote.network_utils.NetworkKeys.IMAGE_URL
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.utils.UIMapper

class MoviesDtoMapper : UIMapper<MoviesDto, PagingData<MoviesDomainModel>> {
    override fun invoke(model: MoviesDto): PagingData<MoviesDomainModel> {
        return PagingData.from(
            model.results.map { resultDto ->
                MoviesDomainModel(
                    id = resultDto.id,
                    title = resultDto.title,
                    rating = resultDto.voteAverage,
                    poster = IMAGE_URL + resultDto.posterPath,
                    releaseDate = resultDto.releaseDate.dropLast(6)
                )
            }
        )
    }
}
