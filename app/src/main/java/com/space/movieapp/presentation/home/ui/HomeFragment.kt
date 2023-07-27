package com.space.movieapp.presentation.home.ui

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.space.movieapp.R
import com.space.movieapp.databinding.FragmentHomeBinding
import com.space.movieapp.presentation.home.adapter.MoviesPagingAdapter
import com.space.movieapp.presentation.base.BaseFragment
import com.space.movieapp.presentation.home.vm.HomeViewModel
import com.space.movieapp.presentation.views.LoadStateDialog
import com.space.movieapp.utils.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<HomeViewModel>() {

    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    private val moviesPagingAdapter by lazy {
        MoviesPagingAdapter()
    }
    private var loadStateDialog: LoadStateDialog? = null

    private val binding by viewBinding(FragmentHomeBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_home

    override fun onBind() {
        navigationToFav()
        initRecyclerView()
        setPopularOrNot()
        observeMoviesByType(POPULAR)
    }

    private fun initRecyclerView() {
        binding.homeMoviesRecyclerView.apply {
            adapter = moviesPagingAdapter
        }
    }

    private fun observeMoviesByType(movieType: String) {
        if (loadStateDialog == null) {
            loadStateDialog = LoadStateDialog(requireContext())
            loadStateDialog?.setCancelable(false)
        }
        lifecycleScope.launch {
            moviesPagingAdapter.loadStateFlow.collect { loadStates ->
                when (loadStates.refresh) {
                    is LoadState.Loading -> {
                        loadStateDialog?.showProgressBar()
                    }
                    is LoadState.Error -> {
                        loadStateDialog?.apply {
                            showErrorDialog()
                            setRefreshButton {
                                moviesPagingAdapter.retry()
                            }
                        }
                    }
                    else -> {
                        loadStateDialog?.dismiss()
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.getMovies(movieType, PAGE_SIZE).collectLatest { pagingData ->
                moviesPagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun setPopularOrNot() {
        with(binding) {
            customSearchView.setPopularMoviesChipClickListener {
                observeMoviesByType(POPULAR)
            }

            customSearchView.setTopRatedMoviesChipClickListener {
                observeMoviesByType(TOP_RATED)
            }
        }
    }
    private fun navigationToFav() {
        binding.movieTextview.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
        }
    }

    companion object {
        const val POPULAR = "movie/popular"
        const val TOP_RATED = "movie/top_rated"
    }
}

