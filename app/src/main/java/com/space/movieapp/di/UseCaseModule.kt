package com.space.movieapp.di

import com.space.movieapp.domain.usecase.get_Movies.GetMoviesUseCase
import com.space.movieapp.domain.usecase.get_favorites_usecase.GetFavoriteMoviesUseCase
import com.space.movieapp.domain.usecase.is_favorites_usecase.IsMovieInFavoritesUseCase
import com.space.movieapp.domain.usecase.toggle_favorites_usecase.ToggleFavoriteMovieUseCase
import org.koin.dsl.module

val useCaseModule = module{
    single { GetMoviesUseCase(get()) }
    single { GetFavoriteMoviesUseCase(get()) }
    single { ToggleFavoriteMovieUseCase(get()) }
    single { IsMovieInFavoritesUseCase(get()) }
}