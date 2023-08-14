package com.space.movieapp.di

import com.space.movieapp.domain.usecase.favorites.delete.DeleteFavoriteMovieUseCase
import com.space.movieapp.domain.usecase.favorites.get_all.GetAllFavoriteMoviesUseCase
import com.space.movieapp.domain.usecase.favorites.isfavorite.IsFavoriteMovieUseCase
import com.space.movieapp.domain.usecase.search.SearchMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module{
    single { GetMoviesUseCase(get()) }
    single { IsFavoriteMovieUseCase(get()) }
    single { DeleteFavoriteMovieUseCase(get()) }
    single { GetAllFavoriteMoviesUseCase(get()) }
    single { SearchMoviesUseCase(get()) }
}