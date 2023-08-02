package com.space.movieapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoritesEntity(
    @PrimaryKey()
    val id: Int,
    val genres: String,
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
