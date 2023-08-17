package com.space.movieapp.presentation.details.vm

import androidx.lifecycle.viewModelScope
import com.space.movieapp.domain.model.DetailsDomainModel
import com.space.movieapp.domain.model.mapper.DetailsToMoviesDomainMapper
import com.space.movieapp.domain.usecase.details.GetDetailsUseCase
import com.space.movieapp.domain.usecase.favorites.check_favorites.CheckFavoritesUseCase
import com.space.movieapp.domain.usecase.favorites.isFavorite.IsFavoriteMovieUseCase
import com.space.movieapp.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getDetailsUseCase: GetDetailsUseCase,
    private val isFavoriteMovieUseCase: IsFavoriteMovieUseCase,
    private val detailsToMoviesDomainMapper: DetailsToMoviesDomainMapper,
    private val checkFavoriteMovieUseCase: CheckFavoritesUseCase,
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
    suspend fun setFavoriteMovie(details: DetailsDomainModel): Boolean {
        val result = detailsToMoviesDomainMapper(details)
        return checkFavoriteMovieUseCase.invoke(result)
    }

    fun manageFavoriteMovie(details: DetailsDomainModel) {
        val result = detailsToMoviesDomainMapper(details)
        viewModelScope.launch {
            isFavoriteMovieUseCase.invoke(result)
        }
    }

    fun navigateToBack() {
        navigateBack()
    }
}