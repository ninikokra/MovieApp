package com.space.movieapp.presentation.home.adapter

import com.space.movieapp.presentation.data.model.MoviesUIModel

interface FavoriteIconClickListener {
    fun onFavoriteIconClick(movie: MoviesUIModel.ResultUI)
}