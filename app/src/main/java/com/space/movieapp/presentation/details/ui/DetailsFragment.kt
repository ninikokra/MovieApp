package com.space.movieapp.presentation.details.ui

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.space.movieapp.R
import com.space.movieapp.databinding.FragmentDetailsBinding
import com.space.movieapp.presentation.base.BaseFragment
import com.space.movieapp.presentation.details.vm.DetailsViewModel
import com.space.movieapp.utils.formatOneDecimal
import com.space.movieapp.utils.setImage
import com.space.movieapp.utils.timeFormatter
import com.space.movieapp.utils.viewBinding
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
        setUpDetails(movieId)
    }

    private fun setUpDetails(movieId: Int) {
        var isFavoriteIconCLicked = false
        viewModel.fetchMovieDetails(movieId)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.detailsState.collect { details ->
                if (details != null) {
                    with(binding) {
                        detailsMovieTitleTextView.text = details.originalTitle
                        ratingTextView.text = details.voteAverage.formatOneDecimal()
                        genreTextView.text = details.genres.first()
                        durationTextView.text = details.runtime.timeFormatter()
                        releasedYearTextview.text = details.getFormattedReleaseDate()
                        aboutMovieTextView.text = details.overview
                        favoritesMoviePoster.setImage(details.getFullPosterUrl())
                        backButton.setOnClickListener {
                            viewModel.navigateToBack()
                        }
                        favoritesIcHeart.setOnClickListener {
                            viewModel.toggleFavoriteMovie(details)
                            isFavoriteIconCLicked = !isFavoriteIconCLicked
                            if (isFavoriteIconCLicked) {
                                favoritesIcHeart.setImageResource(R.drawable.ic_yello_hearts_filled_big)
                            } else {
                                favoritesIcHeart.setImageResource(R.drawable.ic_yellow_heart_big)
                            }
                        }
                    }
                }
            }
        }
    }
}