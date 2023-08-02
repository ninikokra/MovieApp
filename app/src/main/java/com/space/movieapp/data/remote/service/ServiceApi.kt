package com.space.movieapp.data.remote.service

import com.space.movieapp.data.remote.model.GenresDto
import com.space.movieapp.data.remote.model.MoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {
    @GET("{category}")
    suspend fun getMovies(@Path("category") category: String, @Query("page") page: Int): Response<MoviesDto>

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): Response<GenresDto>
}