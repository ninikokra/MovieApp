package com.space.movieapp.presentation.home.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.movieapp.databinding.MoviesRvItemsBinding
import com.space.movieapp.presentation.data.model.MoviesUIModel
import com.space.movieapp.utils.*

class MoviesPagingAdapter :
    PagingDataAdapter<MoviesUIModel.ResultUI, MoviesPagingAdapter.MovieViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent.viewBinding(MoviesRvItemsBinding::inflate))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }
    }

    class MovieViewHolder(private val binding: MoviesRvItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesUIModel.ResultUI) {
            with(binding) {
                var favoritesmIconClickListener: FavoriteIconClickListener? = null

                movieTitleTextview.text = movie.title
                releasedYearTextview.text = movie.getFormattedReleaseDate()
                posterImageView.setImage(movie.getFullPosterUrl())
                genreOnPosterTextView.text = movie.genreIds.first()
                setFavoriteHeartIcon.setOnClickListener {
                    favoritesmIconClickListener?.onFavoriteIconClick(movie)
                    setFavoriteHeartIcon.toggleFavoriteHeartIcons()

                }

            }
        }
    }
}