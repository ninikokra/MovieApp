package com.space.movieapp.data.remote.model

data class GetMoviesParams(
    val category: String,
    val page: Int
)

data class GetSearchQuery(val query: String)
