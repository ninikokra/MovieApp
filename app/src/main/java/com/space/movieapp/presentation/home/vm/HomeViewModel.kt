package com.space.movieapp.presentation.home.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.space.movieapp.domain.usecase.getMovies.GetMoviesUseCase
import com.space.movieapp.presentation.base.BaseViewModel
import com.space.movieapp.presentation.data.mapper.MoviesDomainUIMapper
import com.space.movieapp.presentation.data.model.MoviesUIModel
import com.space.movieapp.presentation.home.ui.HomeFragmentDirections
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val moviesDomainUIMapper: MoviesDomainUIMapper
) : BaseViewModel() {

    suspend fun getMovies(category: String, pageSize: Int): Flow<PagingData<MoviesUIModel.ResultUI>> {
        return getMoviesUseCase.invoke(GetMoviesUseCase.Params(category, pageSize))
            .map { pagingData -> pagingData.map { moviesDomainUIMapper.invoke(it) } }
            .cachedIn(viewModelScope)
    }

    fun navigationToFav() {
        navigate(HomeFragmentDirections.actionHomeFragmentToFavoritesFragment())
    }
}