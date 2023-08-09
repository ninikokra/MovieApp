package com.space.movieapp.presentation.splash_screen

import androidx.lifecycle.viewModelScope
import com.space.movieapp.presentation.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : BaseViewModel() {

    fun navigateToHome() {
        viewModelScope.launch {
            delay(SPLASH_SCREEN_DELAY)
            navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        }
    }

    companion object {
        private const val SPLASH_SCREEN_DELAY = 3000L
    }
}