package com.space.movieapp.presentation.splash_screen

import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.space.movieapp.R
import com.space.movieapp.databinding.FragmentSplashBinding
import com.space.movieapp.presentation.base.BaseFragment
import com.space.movieapp.presentation.favorites.vm.FavoritesViewModel
import com.space.movieapp.utils.viewBinding
import kotlin.reflect.KClass

class SplashFragment : BaseFragment<SplashScreenViewModel>() {
    override val viewModelClass: KClass<SplashScreenViewModel>
        get() = SplashScreenViewModel::class

    private val binding by viewBinding(FragmentSplashBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_splash

    override fun onBind() {
        setSplashScreen()
    }

    private fun setSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }, SPLASH_SCREEN_DELAY)
    }

    companion object {
        private const val SPLASH_SCREEN_DELAY = 3000L
    }
}