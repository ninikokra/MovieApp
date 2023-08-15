package com.space.movieapp.presentation.favorites.ui

import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import com.space.movieapp.R
import com.space.movieapp.databinding.FragmentFavoritesBinding
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.presentation.base.BaseFragment
import com.space.movieapp.presentation.favorites.adapter.FavoritesAdapter
import com.space.movieapp.presentation.favorites.vm.FavoritesViewModel
import com.space.movieapp.utils.isVisible
import com.space.movieapp.utils.viewBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.reflect.KClass

class FavoritesFragment : BaseFragment<FavoritesViewModel>() {

    override val viewModelClass: KClass<FavoritesViewModel>
        get() = FavoritesViewModel::class

    private val favoritesAdapter by lazy {
        FavoritesAdapter()
    }

    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_favorites

    override fun onBind() {
        initRecyclerView()
        observeFavMoviesAndVisibility()
        navigateToDetails()
        backButton()
    }

    private fun initRecyclerView() {
        with(binding) {
            favoriteMoviesRecyclerView.adapter = favoritesAdapter
        }
    }

    private fun observeFavMoviesAndVisibility() {
        viewModel.favoriteMoviesFlow
            .onEach { favoriteMovies ->
                updateUIForMovies(favoriteMovies)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        favoritesAdapter.setOnIconClickListener { movie ->
            viewModel.deleteFavMovie(movie)
        }
    }

    private fun updateUIForMovies(movies: List<MoviesDomainModel.ResultDomain>) {
        favoritesAdapter.submitList(movies)
        binding.noMoviesImageView.isVisible(movies.isEmpty())
    }
    private fun navigateToDetails(){
        favoritesAdapter.setOnItemClickListener {
            viewModel.navigateToDetails(it.id)
        }
    }
    private fun backButton() {
        requireActivity().onBackPressedDispatcher.addCallback {
            viewModel.navigateToBack()
        }
    }
}