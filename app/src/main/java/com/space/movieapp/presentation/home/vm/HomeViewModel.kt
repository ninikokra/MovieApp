package com.space.movieapp.presentation.home.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.space.movieapp.data.local.entity.FavoritesEntity
import com.space.movieapp.data.remote.model.GetMoviesParams
import com.space.movieapp.domain.usecase.getMovies.GetMoviesUseCase
import com.space.movieapp.domain.usecase.get_favorites_usecase.GetFavoriteMoviesUseCase
import com.space.movieapp.domain.usecase.is_favorites_usecase.IsMovieInFavoritesUseCase
import com.space.movieapp.domain.usecase.toggle_favorites_usecase.ToggleFavoriteMovieUseCase
import com.space.movieapp.presentation.base.BaseViewModel
import com.space.movieapp.presentation.data.mapper.FavoritesToMoviesMapper
import com.space.movieapp.presentation.data.mapper.MoviesDomainUIMapper
import com.space.movieapp.presentation.data.mapper.MoviesToFavoritesMapper
import com.space.movieapp.presentation.data.model.MoviesUIModel
import com.space.movieapp.presentation.home.ui.HomeFragmentDirections
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.Interceptor.Companion.invoke

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val moviesDomainUIMapper: MoviesDomainUIMapper,
    private val toggleFavoriteMovieUseCase: ToggleFavoriteMovieUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val moviesToFavoritesMapper: MoviesToFavoritesMapper,
    private val favoritesToMoviesMapper: FavoritesToMoviesMapper
) : BaseViewModel() {


    suspend fun getMovies(category: String, page: Int): Flow<PagingData<MoviesUIModel.ResultUI>> {
        val params = GetMoviesParams(category, page)
        return getMoviesUseCase(params)
            .map { pagingData -> pagingData.map { moviesDomainUIMapper.invoke(it) } }
            .cachedIn(viewModelScope)
    }

    /*  suspend fun toggleFavoriteStatus(movie: MoviesUIModel.ResultUI) {
          val favoritesEntity = moviesToFavoritesMapper.invoke(movie)
          toggleFavoriteMovieUseCase(favoritesEntity)
      }*/

    fun navigationToFav() {
        navigate(HomeFragmentDirections.actionHomeFragmentToFavoritesFragment())
    }

/*    suspend fun getAllFavoriteMovies(): Flow<List<MoviesUIModel.ResultUI>> {
        return getFavoriteMoviesUseCase(Unit)
            .map { favoritesList -> favoritesList.map { favoritesToMoviesMapper.invoke(it) } }
    }*/
}