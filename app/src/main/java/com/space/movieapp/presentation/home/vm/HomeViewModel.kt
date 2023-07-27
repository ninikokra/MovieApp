package com.space.movieapp.presentation.home.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.domain.usecase.getMovies.GetMoviesUseCase
import com.space.movieapp.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val getMoviesUseCase: GetMoviesUseCase) : BaseViewModel() {

    suspend fun getMovies(category: String, pageSize: Int): Flow<PagingData<MoviesDomainModel>> {
        return getMoviesUseCase.invoke(GetMoviesUseCase.Params(category, pageSize))
            .cachedIn(viewModelScope)
    }
}