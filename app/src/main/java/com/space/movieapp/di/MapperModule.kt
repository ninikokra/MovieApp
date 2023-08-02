package com.space.movieapp.di

import com.space.movieapp.data.remote.mapper.MoviesDtoToDomainMapper
import com.space.movieapp.presentation.data.mapper.FavoritesToMoviesMapper
import com.space.movieapp.presentation.data.mapper.MoviesDomainUIMapper
import com.space.movieapp.presentation.data.mapper.MoviesToFavoritesMapper
import org.koin.dsl.module

val mapperModule = module {
    single { MoviesDtoToDomainMapper() }
    single { MoviesDomainUIMapper() }
    single { MoviesToFavoritesMapper() }
    single { FavoritesToMoviesMapper() }
}