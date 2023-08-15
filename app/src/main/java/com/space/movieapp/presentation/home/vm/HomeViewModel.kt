package com.space.movieapp.presentation.home.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.space.movieapp.data.remote.model.RequestGetMoviesParams
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.domain.usecase.favorites.isFavorite.IsFavoriteMovieUseCase
import com.space.movieapp.domain.usecase.get_Movies.GetMoviesUseCase
import com.space.movieapp.domain.usecase.search.SearchMoviesUseCase
import com.space.movieapp.presentation.base.BaseViewModel
import com.space.movieapp.presentation.home.ui.HomeFragmentDirections
import com.space.movieapp.utils.MovieCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val IsFavoriteMovieUseCase: IsFavoriteMovieUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : BaseViewModel() {

    private var lastSelectedCategory: MovieCategory = MovieCategory.POPULAR

    suspend fun searchMovies(query: String): Flow<PagingData<MoviesDomainModel.ResultDomain>> {
        return searchMoviesUseCase(query)
            .cachedIn(viewModelScope)
    }

    fun getLastSelectedCategory(): MovieCategory {
        return lastSelectedCategory
    }

    fun setLastSelectedCategory(category: MovieCategory) {
        lastSelectedCategory = category
    }

    suspend fun getMovies(
        category: String,
        page: Int
    ): Flow<PagingData<MoviesDomainModel.ResultDomain>> {
        val params = RequestGetMoviesParams(category, page)
        return getMoviesUseCase(params)
            .cachedIn(viewModelScope)
    }

    fun isFavoriteMovie(movie: MoviesDomainModel.ResultDomain) {
        viewModelScope.launch {
            IsFavoriteMovieUseCase.invoke(movie)
        }
    }

    fun navigationToDetails(movieId: Int) {
        navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieId))
    }
}