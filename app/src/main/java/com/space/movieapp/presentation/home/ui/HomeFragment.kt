package com.space.movieapp.presentation.home.ui

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.space.movieapp.R
import com.space.movieapp.databinding.FragmentHomeBinding
import com.space.movieapp.presentation.home.adapter.MoviesPagingAdapter
import com.space.movieapp.presentation.base.BaseFragment
import com.space.movieapp.presentation.home.vm.HomeViewModel
import com.space.movieapp.presentation.views.CustomSearchBar
import com.space.movieapp.presentation.views.LoadStateDialog
import com.space.movieapp.utils.MovieCategory
import com.space.movieapp.utils.lifecycleScope
import com.space.movieapp.utils.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.reflect.KClass
import com.space.movieapp.utils.OnQuerySubmitListener


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
        navigationToDetails()
        initRecyclerView()
        setCategory()
        setFavoriteListener()
        setupSearchBar()
        observeMoviesByType(MovieCategory.POPULAR)
    }

    private fun initRecyclerView() {
        binding.homeMoviesRecyclerView.apply {
            adapter = moviesPagingAdapter
        }
    }

    private fun observeMoviesByType(movieType: MovieCategory) {
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
            viewModel.getMovies(movieType.value, PAGE_SIZE).collectLatest { pagingData ->
                moviesPagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun setCategory() {
        with(binding) {
            customSearchView.setPopularMoviesChipClickListener {
                observeMoviesByType(MovieCategory.POPULAR)
            }

            customSearchView.setTopRatedMoviesChipClickListener {
                observeMoviesByType(MovieCategory.TOP_RATED)
            }
        }
    }

    private fun navigationToDetails() {
        binding.movieTextview.setOnClickListener {
            viewModel.navigationToDetails()
        }
    }

    private fun setFavoriteListener() {
        moviesPagingAdapter.setOnIconClickListener {
            lifecycleScope {
                viewModel.toggleFavoriteMovie(it)
            }
        }
    }

    private fun setupSearchBar() {
        binding.customSearchView.apply {
            setOnQuerySubmitListener(object : OnQuerySubmitListener {
                override fun onQuerySubmitted(query: String) {
                    performSearch(query)
                }
            })
        }
    }

    private fun performSearch(query: String) {
        lifecycleScope.launch {
            viewModel.searchMovies(query).collectLatest { pagingData ->
                moviesPagingAdapter.submitData(pagingData)
            }
        }
    }
}