package com.space.movieapp.domain.model

import com.space.movieapp.data.remote.network_utils.NetworkKeys

data class DetailsDomainModel(
    val genres: List<String>,
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val voteAverage: Double,
    val backdropPath: String,
    val popularity: Double,
    val voteCount: Int
    )
{
    private fun getImageUrl(backdropPath: String): String {
        return NetworkKeys.IMAGE_URL + backdropPath
    }

    private fun formatDate(date: String): String {
        return date.dropLast(6)
    }

    fun getFormattedReleaseDate(): String {
        return formatDate(releaseDate)
    }

    fun getFullPosterUrl(): String {
        return getImageUrl(backdropPath)
    }
}
