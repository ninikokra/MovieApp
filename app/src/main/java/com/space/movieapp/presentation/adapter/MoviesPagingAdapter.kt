package com.space.movieapp.presentation.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.movieapp.R
import com.space.movieapp.data.remote.network_utils.NetworkKeys.IMAGE_URL
import com.space.movieapp.databinding.MoviesRvItemsBinding
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.utils.*

class MoviesPagingAdapter :
    PagingDataAdapter<MoviesDomainModel, MoviesPagingAdapter.MovieViewHolder>(DiffCallback()) {

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
        fun bind(movie: MoviesDomainModel) {
            with(binding) {
                movieTitleTextview.text = movie.title
                releasedYearTextview.text = movie.releaseDate
                posterImageView.setImage(IMAGE_URL + movie.poster)
                setFavoriteHeartIcon.setOnClickListener {
                    setFavoriteHeartIcon.toggleFavoriteHeartIcons()
                }
            }
        }
    }
}