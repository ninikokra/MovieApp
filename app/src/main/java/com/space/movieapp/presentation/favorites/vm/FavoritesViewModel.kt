package com.space.movieapp.presentation.favorites.vm

import androidx.lifecycle.viewModelScope
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.domain.usecase.favorites.delete.DeleteFavoriteMovieUseCase
import com.space.movieapp.domain.usecase.favorites.get_all.GetAllFavoriteMoviesUseCase
import com.space.movieapp.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getAllFavoriteMoviesUseCase: GetAllFavoriteMoviesUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase
) : BaseViewModel() {

    private val _favoriteMoviesFlow = MutableStateFlow<List<MoviesDomainModel.ResultDomain>>(emptyList())
    val favoriteMoviesFlow: Flow<List<MoviesDomainModel.ResultDomain>> = _favoriteMoviesFlow

    init {
        setFavMovies()
    }

    private fun setFavMovies() {
        viewModelScope.launch {
            getAllFavoriteMoviesUseCase().collect { favoriteMovies ->
                _favoriteMoviesFlow.value = favoriteMovies
            }
        }
    }
    fun deleteFavMovie(movie: MoviesDomainModel.ResultDomain) {
        viewModelScope.launch {
            deleteFavoriteMovieUseCase(movie.id)
        }
    }
}