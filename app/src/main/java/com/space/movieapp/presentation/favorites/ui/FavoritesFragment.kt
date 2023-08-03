package com.space.movieapp.presentation.favorites.ui

import com.space.movieapp.R
import com.space.movieapp.databinding.FragmentFavoritesBinding
import com.space.movieapp.presentation.base.BaseFragment
import com.space.movieapp.presentation.favorites.vm.FavoritesViewModel
import com.space.movieapp.presentation.home.adapter.MoviesPagingAdapter
import com.space.movieapp.utils.viewBinding
import kotlin.reflect.KClass


class FavoritesFragment : BaseFragment<FavoritesViewModel>() {

    override val viewModelClass: KClass<FavoritesViewModel>
        get() = FavoritesViewModel::class

    private val favoritesAdapter by lazy {
        MoviesPagingAdapter()
    }

    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_favorites

    override fun onBind() {
    }
}