package com.space.movieapp.di

import com.space.movieapp.data.remote.mapper.MoviesDtoMapper
import org.koin.dsl.module

val mapperModule = module {
    single { MoviesDtoMapper() }
}