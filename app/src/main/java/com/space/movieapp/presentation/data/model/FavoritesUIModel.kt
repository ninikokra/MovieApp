package com.space.movieapp.presentation.data.model

data class FavoritesUIModel(
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
)