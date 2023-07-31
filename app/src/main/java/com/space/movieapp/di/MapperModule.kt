package com.space.movieapp.di

import com.space.movieapp.data.remote.mapper.MoviesDtoToDomainMapper
import com.space.movieapp.presentation.data.mapper.MoviesDomainUIMapper
import org.koin.dsl.module

val mapperModule = module {
    single { MoviesDtoToDomainMapper() }
    single { MoviesDomainUIMapper() }
}