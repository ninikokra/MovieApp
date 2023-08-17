package com.space.movieapp.di

import com.space.movieapp.data.local.mapper.FavoritesEntityToDomainMapper
import com.space.movieapp.data.local.mapper.MoviesDomainToFavoritesEntityMapper
import com.space.movieapp.data.remote.mapper.DetailsDtoToDomainMapper
import com.space.movieapp.data.remote.mapper.MoviesDtoToDomainMapper
import com.space.movieapp.domain.model.mapper.DetailsToMoviesDomainMapper
import com.space.movieapp.presentation.model.mapper.MoviesDomainToUIMapper
import com.space.movieapp.presentation.model.mapper.MoviesUIToDomainMapper
import org.koin.dsl.module

val mapperModule = module {
    single { MoviesDtoToDomainMapper() }
    single { MoviesDomainToUIMapper() }
    single { MoviesUIToDomainMapper() }
    single { MoviesDomainToFavoritesEntityMapper()}
    single { FavoritesEntityToDomainMapper() }
    single { DetailsDtoToDomainMapper()}
    single { DetailsToMoviesDomainMapper() }
}