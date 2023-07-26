package com.space.movieapp.presentation.home.ui

import androidx.navigation.fragment.findNavController
import com.space.movieapp.R
import com.space.movieapp.databinding.FragmentHomeBinding
import com.space.movieapp.presentation.base.BaseFragment
import com.space.movieapp.presentation.base.Inflater
import com.space.movieapp.presentation.home.vm.HomeViewModel
import com.space.movieapp.utils.viewBinding

class HomeFragment : BaseFragment<HomeViewModel>() {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_home

    override fun onBind() {
        navigationToFav()
    }

    private fun navigationToFav() {
        binding.movieTextview.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
        }
    }
}

