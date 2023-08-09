package com.space.movieapp.di

import com.space.movieapp.presentation.details.vm.DetailsViewModel
import com.space.movieapp.presentation.favorites.vm.FavoritesViewModel
import com.space.movieapp.presentation.home.vm.HomeViewModel
import com.space.movieapp.presentation.splash_screen.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        SplashScreenViewModel()
    }
    viewModel{
        HomeViewModel(get(),get())
    }
    viewModel{
        FavoritesViewModel()
    }
    viewModel{
        DetailsViewModel()
    }
}