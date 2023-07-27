package com.space.movieapp.domain.model

data class MoviesDomainModel(
    val id: Int,
    val title: String,
    val rating: Double,
    val poster: String,
    val releaseDate: String
)