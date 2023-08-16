package com.space.movieapp.presentation.favorites.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.movieapp.R
import com.space.movieapp.databinding.MoviesItemsBinding
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.utils.*

class FavoritesAdapter :
    ListAdapter<MoviesDomainModel.ResultDomain, FavoritesAdapter.FavoritesViewHolder>(DiffCallback()) {

    var onItemClickListener: ((MoviesDomainModel.ResultDomain) -> Unit)? = null
    var onIconCLickListener: ((MoviesDomainModel.ResultDomain) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val viewHolder = FavoritesViewHolder(parent.viewBinding(MoviesItemsBinding::inflate))
        viewHolder.binding.root.setOnClickListener {
            getItem(viewHolder.adapterPosition)?.let {
                onItemClickListener?.invoke(it)
            }
        }
        viewHolder.binding.setFavoriteHeartIcon.setOnClickListener {
            getItem(viewHolder.adapterPosition)?.let {
                onIconCLickListener?.invoke(it)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }
    }

    class FavoritesViewHolder(val binding: MoviesItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesDomainModel.ResultDomain) {
            with(binding) {
                movieTitleTextview.text = movie.title
                releasedYearTextview.text = movie.getFormattedReleaseDate()
                posterImageView.setImage(movie.getFullPosterUrl())
                genreOnPosterTextView.text = movie.genreIds?.first() ?:
                genreOnPosterTextView.getStringRes(R.string.unknown_genre_text)
                setFavoriteHeartIcon.setImageResource(R.drawable.ic_littel_yellow_heart_filled)
            }
        }
    }
}