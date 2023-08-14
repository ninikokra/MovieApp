package com.space.movieapp.presentation.model.model

import com.space.movieapp.data.remote.model.MoviesDto
import com.space.movieapp.data.remote.network_utils.NetworkKeys

class MoviesUIModel(
    val page: Int,
    val results: List<MoviesDto.ResultDto>,
    val totalPages: Int,
    val totalResults: Int
) {
    data class ResultUI(
        val adult: Boolean,
        val backdropPath: String,
        val genreIds: List<String>,
        val originalLanguage: String,
        val originalTitle: String,
        val overview: String,
        val popularity: Double,
        val posterPath: String,
        val video: Boolean,
        val voteAverage: Double,
        val voteCount: Int,
        val id: Int,
        val title: String,
        val releaseDate: String,
    ){
        private fun getImageUrl(posterPath: String): String {
            return NetworkKeys.IMAGE_URL + posterPath
        }

        private fun formatDate(date: String): String {
            return date.dropLast(6)
        }

        fun getFormattedReleaseDate(): String {
            return formatDate(releaseDate)
        }

        fun getFullPosterUrl(): String {
            return getImageUrl(posterPath)
        }
    }
}
