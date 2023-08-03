package com.space.movieapp.domain.model

import com.space.movieapp.data.remote.network_utils.NetworkKeys

data class FavoritesDomainModel(
    val id: Int,
    val genreIds: String,
    val title: String,
    val voteAverage: Double,
    val releaseDate: String,
    val posterPath: String,
    val adult: Boolean,
    val backdropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val video: Boolean,
    val voteCount: Int,
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