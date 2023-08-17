package com.space.movieapp.domain.model

data class MoviesDomainModel(
    val page: Int,
    val results: List<ResultDomain>,
    val totalPages: Int,
    val totalResults: Int
) {
    data class ResultDomain(
        val adult: Boolean,
        val backdropPath: String,
        val genreIds: List<String>?,
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
        val isFavorite: Boolean = false
    )
}