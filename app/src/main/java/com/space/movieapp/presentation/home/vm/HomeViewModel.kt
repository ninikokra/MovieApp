package com.space.movieapp.presentation.home.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.space.movieapp.data.remote.model.GetMoviesParams
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.domain.usecase.get_Movies.GetMoviesUseCase
import com.space.movieapp.domain.usecase.favorites.toggle.ToggleFavoriteMovieUseCase
import com.space.movieapp.presentation.base.BaseViewModel
import com.space.movieapp.presentation.home.ui.HomeFragmentDirections
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val toggleFavoriteMovieUseCase: ToggleFavoriteMovieUseCase,
) : BaseViewModel() {

    suspend fun getMovies(
        category: String,
        page: Int
    ): Flow<PagingData<MoviesDomainModel.ResultDomain>> {
        val params = GetMoviesParams(category, page)
        return getMoviesUseCase(params)
            .cachedIn(viewModelScope)
    }

    fun toggleFavoriteMovie(movie: MoviesDomainModel.ResultDomain) {
        viewModelScope.launch {
            toggleFavoriteMovieUseCase.invoke(movie)
        }
    }

    fun navigationToDetails() {
        navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment())
    }
}