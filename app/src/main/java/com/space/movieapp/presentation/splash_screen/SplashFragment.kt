package com.space.movieapp.presentation.splash_screen

import com.space.movieapp.R
import com.space.movieapp.databinding.FragmentSplashBinding
import com.space.movieapp.presentation.base.BaseFragment
import com.space.movieapp.utils.viewBinding
import kotlin.reflect.KClass

class SplashFragment : BaseFragment<SplashScreenViewModel>() {
    override val viewModelClass: KClass<SplashScreenViewModel>
        get() = SplashScreenViewModel::class

    private val binding by viewBinding(FragmentSplashBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_splash

    override fun onBind() {
        navigateToHome()
    }

    private fun navigateToHome() {
        viewModel.navigateToHome()
    }
}