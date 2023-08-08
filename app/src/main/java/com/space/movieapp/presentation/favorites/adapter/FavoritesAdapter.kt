package com.space.movieapp.presentation.favorites.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.movieapp.R
import com.space.movieapp.databinding.MoviesRvItemsBinding
import com.space.movieapp.domain.model.MoviesDomainModel
import com.space.movieapp.utils.DiffCallback
import com.space.movieapp.utils.setImage
import com.space.movieapp.utils.viewBinding

class FavoritesAdapter :
    ListAdapter<MoviesDomainModel.ResultDomain, FavoritesAdapter.FavoritesViewHolder>(DiffCallback()) {

    private var onFavoriteIconCLick: ((MoviesDomainModel.ResultDomain) -> Unit)? = null

    fun setOnIconClickListener(listener: (MoviesDomainModel.ResultDomain) -> Unit) {
        onFavoriteIconCLick = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(parent.viewBinding(MoviesRvItemsBinding::inflate))
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie,onFavoriteIconCLick)
        }
    }

    class FavoritesViewHolder(private val binding: MoviesRvItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesDomainModel.ResultDomain,
                 onFavoriteClicked: ((MoviesDomainModel.ResultDomain) -> Unit)?
        ) {
            with(binding) {
                movieTitleTextview.text = movie.title
                releasedYearTextview.text = movie.getFormattedReleaseDate()
                posterImageView.setImage(movie.getFullPosterUrl())
                genreOnPosterTextView.text = movie.genreIds.first()
                setFavoriteHeartIcon.setImageResource(R.drawable.ic_littel_yellow_heart_filled)
                setFavoriteHeartIcon.setOnClickListener {
                    onFavoriteClicked?.invoke(movie)
                }
            }
        }
    }
}
