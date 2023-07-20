package com.space.movieapp.presentation.home.ui

import androidx.navigation.fragment.findNavController
import com.space.movieapp.R
import com.space.movieapp.databinding.FragmentHomeBinding
import com.space.movieapp.presentation.base.BaseFragment
import com.space.movieapp.presentation.base.Inflater
import com.space.movieapp.presentation.home.vm.HomeViewModel
import com.space.movieapp.presentation.views.BottomNavigationClickListener
import com.space.movieapp.presentation.views.CustomBottomNavigation

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    BottomNavigationClickListener {

    override fun inflate(): Inflater<FragmentHomeBinding> {
        return FragmentHomeBinding::inflate
    }

    override fun onBind() {
        setBottomNavigation()
        navigationToFav()
    }

    override fun onHomeButtonClicked() {}

    override fun onFavoritesButtonClicked() {
        findNavController().navigate(R.id.action_homeFragment_to_favoritesFragment)
    }

    private fun setBottomNavigation() {
        binding.customBottomNav.setBottomNavigationClickListener(this)
        binding.customBottomNav.updateButtonStates(true)
    }

    private fun navigationToFav() {
        binding.movieTextview.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
        }
    }
}

