package com.space.movieapp.presentation.home.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.space.movieapp.data.local.mapper.MoviesDtoToFavoritesDomainMapper
import com.space.movieapp.data.remote.model.GetMoviesParams
import com.space.movieapp.data.remote.model.MoviesDto
import com.space.movieapp.domain.model.FavoritesDomainModel
import com.space.movieapp.domain.usecase.get_Movies.GetMoviesUseCase
import com.space.movieapp.domain.usecase.save.SaveOrDeleteFavoriteMovieUseCase
import com.space.movieapp.domain.usecase.toggle_favorites_usecase.ToggleFavoriteMovieUseCase
import com.space.movieapp.presentation.base.BaseViewModel
import com.space.movieapp.presentation.data.mapper.MoviesDomainToUIMapper
import com.space.movieapp.presentation.data.mapper.MoviesUIToDomainMapper
import com.space.movieapp.presentation.data.mapper.MoviesUIToFavoritesDomainMapper
import com.space.movieapp.presentation.data.model.MoviesUIModel
import com.space.movieapp.presentation.home.ui.HomeFragmentDirections
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val moviesDomainUIMapper: MoviesDomainToUIMapper,
    private val moviesUIToFavoritesDomainMapper: MoviesUIToFavoritesDomainMapper,
    private val toggleFavoriteMovieUseCase: ToggleFavoriteMovieUseCase,
    private val saveOrDeleteFavoriteMovieUseCase: SaveOrDeleteFavoriteMovieUseCase,
    private val moviesDtoToFavoritesDomainMapper: MoviesDtoToFavoritesDomainMapper,

) : BaseViewModel() {

    suspend fun getMovies(category: String, page: Int): Flow<PagingData<MoviesUIModel.ResultUI>> {
        val params = GetMoviesParams(category, page)
        return getMoviesUseCase(params)
            .map { pagingData -> pagingData.map { moviesDomainUIMapper.invoke(it) } }
            .cachedIn(viewModelScope)
    }
    fun saveOrDeleteMovieAsFavorite(movie: MoviesUIModel) {
        viewModelScope.launch {
            val favoritesDomainModel = moviesDtoToFavoritesDomainMapper(movie)
            saveOrDeleteFavoriteMovieUseCase(favoritesDomainModel)
    }
}

    fun toggleFavoriteStatus(movie: MoviesUIModel.ResultUI) {
        viewModelScope.launch {
            val moviesDomainModel = moviesUIToFavoritesDomainMapper(movie)
            toggleFavoriteMovieUseCase(moviesDomainModel)
        }
    }
    fun navigationToFav() {
        navigate(HomeFragmentDirections.actionHomeFragmentToFavoritesFragment())
    }
}