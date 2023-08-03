package com.space.movieapp.presentation.favorites.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.movieapp.databinding.MoviesRvItemsBinding
import com.space.movieapp.domain.model.FavoritesDomainModel
import com.space.movieapp.presentation.home.adapter.FavoriteIconClickListener
import com.space.movieapp.utils.DiffCallback
import com.space.movieapp.utils.setImage
import com.space.movieapp.utils.toggleFavoriteHeartIcons
import com.space.movieapp.utils.viewBinding

class FavoritesAdapter :
    ListAdapter<FavoritesDomainModel, FavoritesAdapter.FavoritesViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(parent.viewBinding(MoviesRvItemsBinding::inflate))
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }
    }

    class FavoritesViewHolder(private val binding: MoviesRvItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: FavoritesDomainModel) {
            val favoritesIconClickListener: FavoriteIconClickListener? = null
            with(binding) {
                movieTitleTextview.text = movie.title
                releasedYearTextview.text = movie.getFormattedReleaseDate()
                posterImageView.setImage(movie.getFullPosterUrl())
                genreOnPosterTextView.text = movie.genreIds
               /* setFavoriteHeartIcon.setOnClickListener {
                    favoritesIconClickListener?.onFavoriteIconClick(movie)
                    setFavoriteHeartIcon.toggleFavoriteHeartIcons()
                }*/
            }
        }
    }
}
