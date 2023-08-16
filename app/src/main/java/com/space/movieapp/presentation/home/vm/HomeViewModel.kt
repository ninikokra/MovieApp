package com.space.movieapp.presentation.home.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.space.movieapp.data.remote.model.RequestGetMoviesParams
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.domain.usecase.favorites.check_favorites.CheckFavoritesUseCase
import com.space.movieapp.domain.usecase.favorites.isFavorite.IsFavoriteMovieUseCase
import com.space.movieapp.domain.usecase.get_Movies.GetMoviesUseCase
import com.space.movieapp.domain.usecase.search.SearchMoviesUseCase
import com.space.movieapp.presentation.base.BaseViewModel
import com.space.movieapp.presentation.home.ui.HomeFragmentDirections
import com.space.movieapp.utils.MovieCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val isFavoriteMovieUseCase: IsFavoriteMovieUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val checkFavoritesUseCase: CheckFavoritesUseCase
) : BaseViewModel() {

    private var lastSelectedCategory: MovieCategory = MovieCategory.POPULAR

    fun getLastSelectedCategory(): MovieCategory {
        return lastSelectedCategory
    }

    fun setLastSelectedCategory(category: MovieCategory) {
        lastSelectedCategory = category
    }

    suspend fun searchMoviesWithFavoriteStatus(query: String): Flow<PagingData<MoviesDomainModel.ResultDomain>> {
        return searchMoviesUseCase(query).map { pagingData ->
            pagingData.map { movie ->
                val isFavorite = checkFavoritesUseCase.invoke(movie)
                movie.copy(isFavorite = isFavorite)
            }
        }.cachedIn(viewModelScope)
    }

    suspend fun getMoviesWithFavoriteStatus(
        category: String,
        page: Int
    ): Flow<PagingData<MoviesDomainModel.ResultDomain>> {
        val params = RequestGetMoviesParams(category, page)

        val moviesFlow = getMoviesUseCase(params).cachedIn(viewModelScope)
        val updatedMoviesFlow = moviesFlow.map { pagingData ->
            pagingData.map { movie ->
                val isFavorite = checkFavoritesUseCase.invoke(movie)
                movie.copy(isFavorite = isFavorite)
            }
        }

        return updatedMoviesFlow
    }

    fun isFavoriteMovie(movie: MoviesDomainModel.ResultDomain) {
        viewModelScope.launch {
            val updatedMovie = movie.copy(isFavorite = !movie.isFavorite)
            isFavoriteMovieUseCase.invoke(updatedMovie)
        }
    }

    fun navigationToDetails(movieId: Int) {
        navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieId))
    }

    fun navigateToBack() {
        navigateBack()
    }
}