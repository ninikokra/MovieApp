package com.space.movieapp.presentation.details.vm

import androidx.lifecycle.viewModelScope
import com.space.movieapp.domain.model.DetailsDomainModel
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.domain.model.mapper.DetailsToMoviesDomainMapper
import com.space.movieapp.domain.usecase.details.GetDetailsUseCase
import com.space.movieapp.domain.usecase.favorites.toggle.ToggleFavoriteMovieUseCase
import com.space.movieapp.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getDetailsUseCase: GetDetailsUseCase,
    private val toggleFavoriteMovieUseCase: ToggleFavoriteMovieUseCase,
    private val detailsToMoviesDomainMapper: DetailsToMoviesDomainMapper
    ) : BaseViewModel() {

    private val _detailsState = MutableStateFlow<DetailsDomainModel?>(null)
    val detailsState: StateFlow<DetailsDomainModel?> = _detailsState

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            getDetailsUseCase(movieId).collect { details ->
                _detailsState.value = details
            }
        }
    }

    fun toggleFavoriteMovie(details: DetailsDomainModel) {
        val result = detailsToMoviesDomainMapper(details)
        viewModelScope.launch {
            toggleFavoriteMovieUseCase.invoke(result)
        }
    }

    fun navigateToBack() {
        navigateBack()
    }
}