package com.space.movieapp.presentation.favorites.ui

import androidx.navigation.fragment.findNavController
import com.space.movieapp.R
import com.space.movieapp.databinding.FragmentFavoritesBinding
import com.space.movieapp.presentation.base.BaseFragment
import com.space.movieapp.presentation.base.Inflater
import com.space.movieapp.presentation.favorites.vm.FavoritesViewModel
import com.space.movieapp.presentation.views.BottomNavigationClickListener
import com.space.movieapp.presentation.views.CustomBottomNavigation


class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>(),
    BottomNavigationClickListener {


    override fun inflate(): Inflater<FragmentFavoritesBinding> {
        return FragmentFavoritesBinding::inflate
    }

    override fun onBind() {
        setBottomNavigation()
    }

    override fun onHomeButtonClicked() {
        findNavController().navigate(R.id.action_favoritesFragment_to_homeFragment)
    }

    override fun onFavoritesButtonClicked() {}

    private fun setBottomNavigation() {
        binding.bottomNavigateFavorite.setBottomNavigationClickListener(this)
        binding.bottomNavigateFavorite.updateButtonStates(false)
    }
}
