package com.space.movieapp.presentation.home.ui

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.space.movieapp.R
import com.space.movieapp.databinding.FragmentHomeBinding
import com.space.movieapp.presentation.home.adapter.MoviesPagingAdapter
import com.space.movieapp.presentation.base.BaseFragment
import com.space.movieapp.presentation.home.vm.HomeViewModel
import com.space.movieapp.presentation.views.LoadStateDialog
import com.space.movieapp.utils.MovieCategory
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
        initRecyclerView()
        setCategory()
        setListeners()
        setupSearchBar()
        backButton()
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
            viewModel.getMoviesWithFavoriteStatus(movieType.value, PAGE_SIZE).collectLatest { pagingData ->
                moviesPagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun setCategory() {
        with(binding) {
            customSearchView.setPopularMoviesChipClickListener {
                viewModel.setLastSelectedCategory(MovieCategory.POPULAR)
                observeMoviesByType(MovieCategory.POPULAR)
            }

            customSearchView.setTopRatedMoviesChipClickListener {
                viewModel.setLastSelectedCategory(MovieCategory.TOP_RATED)
                observeMoviesByType(MovieCategory.TOP_RATED)
            }
        }
    }


    private fun setListeners() {
        moviesPagingAdapter.setOnIconClickListener {
            viewModel.isFavoriteMovie(it)
        }
        moviesPagingAdapter.setOnItemClickListener {
            viewModel.navigationToDetails(it.id)
        }
    }

    private fun setupSearchBar() {
        binding.customSearchView.setOnQuerySubmitListener(object : OnQuerySubmitListener {
            override fun onQuerySubmitted(query: String) {
                if (query.isNotEmpty()) {
                    performSearch(query)
                }else{
                    val lastSelectedCategory = viewModel.getLastSelectedCategory()
                    observeMoviesByType(lastSelectedCategory)
                }
            }
        })
    }

    private fun performSearch(query: String) {
        lifecycleScope.launch {
            viewModel.searchMoviesWithFavoriteStatus(query).collectLatest { pagingData ->
                moviesPagingAdapter.submitData(pagingData)
            }
        }
    }
    private fun backButton() {
        requireActivity().onBackPressedDispatcher.addCallback {
            viewModel.navigateToBack()
        }
    }
}