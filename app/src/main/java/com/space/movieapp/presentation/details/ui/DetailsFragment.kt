package com.space.movieapp.presentation.details.ui

import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.space.movieapp.R
import com.space.movieapp.databinding.FragmentDetailsBinding
import com.space.movieapp.presentation.base.BaseFragment
import com.space.movieapp.presentation.details.vm.DetailsViewModel
import com.space.movieapp.utils.*
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class DetailsFragment : BaseFragment<DetailsViewModel>() {

    override val viewModelClass: KClass<DetailsViewModel>
        get() = DetailsViewModel::class

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_details

    private val args: DetailsFragmentArgs by navArgs()

    override fun onBind() {
        val movieId = args.movieId
        backButton()
        setUpDetails(movieId)
    }

    private fun updateFavoriteIcon(isFavorite: Boolean) {
        if (isFavorite) {
            binding.favoritesIcHeart.setImageResource(R.drawable.ic_yello_hearts_filled_big)
        } else {
            binding.favoritesIcHeart.setImageResource(R.drawable.ic_yellow_heart_big)
        }
    }
    private fun setUpDetails(movieId: Int) {
        viewModel.fetchMovieDetails(movieId)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.detailsState.collect { details ->
                if (details != null) {
                    with(binding) {
                        detailsMovieTitleTextView.text = details.originalTitle
                        ratingTextView.text = details.voteAverage.formatOneDecimal()
                        durationTextView.text = details.runtime.timeFormatter()
                        releasedYearTextview.text = details.getFormattedReleaseDate()
                        aboutMovieTextView.text = details.overview

                        if (details.backdropPath.isNotEmpty() && details.genres.isNotEmpty()) {
                            favoritesMoviePoster.setImage(details.getFullPosterUrl())
                            genreTextView.text = details.genres.first()
                        } else {
                            favoritesMoviePoster.setImageDrawableResource(R.drawable.bkg_no_image_available)
                            genreTextView.text = genreTextView.getStringRes(R.string.unknown_genre_text)
                        }

                        val isFavorite = viewModel.setFavoriteMovie(details)
                        updateFavoriteIcon(isFavorite)

                        favoritesIcHeart.setOnClickListener {
                            viewModel.manageFavoriteMovie(details)
                            updateFavoriteIcon(!isFavorite)
                        }
                    }
                }
            }
        }
    }
    private fun backButton() {
        requireActivity().onBackPressedDispatcher.addCallback {
            viewModel.navigateToBack()
        }
        binding.backButton.setOnClickListener {
            viewModel.navigateToBack()
        }
    }
}