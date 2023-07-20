package com.space.movieapp.presentation.splash_screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.space.movieapp.R
import com.space.movieapp.databinding.FragmentSplashBinding
import com.space.movieapp.presentation.base.BaseFragment
import com.space.movieapp.presentation.base.Inflater

class SplashFragment : BaseFragment<FragmentSplashBinding,SplashScreenViewModel>() {

    override fun inflate(): Inflater<FragmentSplashBinding> {
        return  FragmentSplashBinding::inflate
    }

    override fun onBind() {
        setSplashScreen()
    }

    private fun setSplashScreen(){
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        },3000)
    }
}