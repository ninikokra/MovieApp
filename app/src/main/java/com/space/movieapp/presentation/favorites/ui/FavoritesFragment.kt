package com.space.movieapp.presentation.favorites.ui

import com.space.movieapp.R
import com.space.movieapp.databinding.FragmentFavoritesBinding
import com.space.movieapp.databinding.FragmentHomeBinding
import com.space.movieapp.presentation.base.BaseFragment
import com.space.movieapp.presentation.base.Inflater
import com.space.movieapp.presentation.favorites.vm.FavoritesViewModel
import com.space.movieapp.utils.viewBinding


class FavoritesFragment : BaseFragment<FavoritesViewModel>() {

    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_favorites

    override fun onBind() {
    }
}
