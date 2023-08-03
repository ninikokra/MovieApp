package com.space.movieapp.di

import com.space.movieapp.data.local.mapper.FavoritesEntityToDomainMapper
import com.space.movieapp.data.remote.mapper.MoviesDtoToDomainMapper
import com.space.movieapp.domain.mapper.FavoritesDomainToEntityMapper
import com.space.movieapp.presentation.data.mapper.FavoritesDomainToMoviesUIMapper
import com.space.movieapp.presentation.data.mapper.MoviesDomainToUIMapper
import com.space.movieapp.presentation.data.mapper.MoviesUIToDomainMapper
import com.space.movieapp.presentation.data.mapper.MoviesUIToFavoritesDomainMapper
import org.koin.dsl.module

val mapperModule = module {
    single { MoviesDtoToDomainMapper() }//+
    single { MoviesDomainToUIMapper() } //+
    single { MoviesUIToDomainMapper() }//+
    single { MoviesUIToFavoritesDomainMapper() }//+
    single { FavoritesDomainToMoviesUIMapper() }//+
    single { FavoritesDomainToEntityMapper() }//+
    single { FavoritesEntityToDomainMapper() }//+
}