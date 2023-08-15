package com.space.movieapp.data.remote.model

data class RequestGetMoviesParams(
    val category: String,
    val page: Int
)