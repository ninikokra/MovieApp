package com.space.movieapp.di

import com.space.movieapp.data.repository.MoviesRepositoryImpl
import com.space.movieapp.domain.repository.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module{
    single<MoviesRepository>{
      MoviesRepositoryImpl(get(),get())
    }
}