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

    var onItemClickListener: ((MoviesDomainModel.ResultDomain) -> Unit)? = null

    var onIconCLickListener: ((MoviesDomainModel.ResultDomain) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val viewHolder = MovieViewHolder(parent.viewBinding(MoviesItemsBinding::inflate))
        viewHolder.binding.setFavoriteHeartIcon.setOnClickListener {
            getItem(viewHolder.adapterPosition)?.let { movie -> onIconCLickListener?.invoke(movie)
                viewHolder.binding.setFavoriteHeartIcon.toggleFavoriteHeartIcons(!movie.isFavorite)
            }
        }
        viewHolder.binding.root.setOnClickListener {
            getItem(viewHolder.adapterPosition)?.let {
                onItemClickListener?.invoke(it)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }
    }

    class MovieViewHolder(
         val binding: MoviesItemsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            movie: MoviesDomainModel.ResultDomain,
        ) {
            with(binding) {
                movieTitleTextview.text = movie.title
                releasedYearTextview.text = movie.getFormattedReleaseDate()

                if (movie.posterPath.isNotEmpty() && !movie.genreIds.isNullOrEmpty()) {
                    posterImageView.setImage(movie.getFullPosterUrl())
                    genreOnPosterTextView.text = movie.genreIds.first()
                } else {
                    posterImageView.setImageDrawableResource(R.drawable.bkg_no_image_available)
                    genreOnPosterTextView.text = itemView.getStringRes(R.string.unknown_genre_text)
                }
                setFavoriteHeartIcon.toggleFavoriteHeartIcons(movie.isFavorite)
            }
        }
    }
}