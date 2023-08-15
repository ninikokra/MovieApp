package com.space.movieapp.domain.model

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
    val voteCount: Int,
)
