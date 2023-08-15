package com.space.movieapp.presentation.home.adapter

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.movieapp.R
import com.space.movieapp.databinding.MoviesItemsBinding
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.utils.*

class MoviesPagingAdapter :
    PagingDataAdapter<MoviesDomainModel.ResultDomain, MoviesPagingAdapter.MovieViewHolder>(
        DiffCallback()
    ) {

    private var onItemClickListener: ((MoviesDomainModel.ResultDomain) -> Unit)? = null
    private var onIconCLickListener: ((MoviesDomainModel.ResultDomain) -> Unit)? = null

    fun setOnIconClickListener(listener: (MoviesDomainModel.ResultDomain) -> Unit) {
        onIconCLickListener = listener
    }


    fun setOnItemClickListener(listener: (MoviesDomainModel.ResultDomain) -> Unit) {
        onItemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent.viewBinding(MoviesItemsBinding::inflate))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie, onItemClickListener,onIconCLickListener)
        }
    }

    class MovieViewHolder(
        private val binding: MoviesItemsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            movie: MoviesDomainModel.ResultDomain,
            onItemClickListener: ((MoviesDomainModel.ResultDomain) -> Unit)?,
            onIconCLickListener: ((MoviesDomainModel.ResultDomain) -> Unit)?

        ) {
            with(binding) {
                movieTitleTextview.text = movie.title
                releasedYearTextview.text = movie.getFormattedReleaseDate()

                if (movie.posterPath.isNotEmpty() && movie.genreIds.isNotEmpty()) {
                    posterImageView.setImage(movie.getFullPosterUrl())
                    genreOnPosterTextView.text = movie.genreIds.first()
                } else {
                    posterImageView.setImageDrawableResource(R.drawable.bkg_no_image_available)
                    genreOnPosterTextView.text = itemView.getStringRes(R.string.unknown_genre_text)
                }
                setFavoriteHeartIcon.setOnClickListener {
                    setFavoriteHeartIcon.toggleFavoriteHeartIcons()
                    onIconCLickListener?.invoke(movie)
                }
                itemView.setOnClickListener {
                    onItemClickListener?.invoke(movie)
                }
            }
        }
    }
}