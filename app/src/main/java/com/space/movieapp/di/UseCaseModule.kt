package com.space.movieapp.di

import com.space.movieapp.domain.usecase.getMovies.GetMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module{
    single { GetMoviesUseCase(get())}
}