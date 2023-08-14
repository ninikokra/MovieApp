package com.space.movieapp

import android.app.Application
import com.space.movieapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MoviesApp)
            modules(
                repositoryModule,useCaseModule,viewModelModule, mapperModule,
                networkModule, dataBaseModule
            )
        }
    }
}