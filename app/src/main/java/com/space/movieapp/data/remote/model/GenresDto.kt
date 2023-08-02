package com.space.movieapp.data.remote.model
import com.google.gson.annotations.SerializedName

data class GenresDto(
    val genres: List<Genre>
){
    data class Genre(
        val id: Int,
        @SerializedName("name")
        val name: String
    )
}