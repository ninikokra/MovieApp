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

    private var onItemClickListener: ((MoviesDomainModel.ResultDomain) -> Unit)? = null
    private var onIconCLickListener: ((MoviesDomainModel.ResultDomain) -> Unit)? = null

    fun setOnIconClickListener(listener: (MoviesDomainModel.ResultDomain) -> Unit) {
        onIconCLickListener = listener
    }
    fun setOnItemClickListener(listener: (MoviesDomainModel.ResultDomain) -> Unit) {
        onItemClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(parent.viewBinding(MoviesItemsBinding::inflate))
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie,onItemClickListener,onIconCLickListener)
        }
    }

    class FavoritesViewHolder(private val binding: MoviesItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesDomainModel.ResultDomain,
                 onItemClickListener: ((MoviesDomainModel.ResultDomain) -> Unit)?,
                 onIconCLickListener: ((MoviesDomainModel.ResultDomain) -> Unit)?
        ) {
            with(binding) {
                movieTitleTextview.text = movie.title
                releasedYearTextview.text = movie.getFormattedReleaseDate()
                posterImageView.setImage(movie.getFullPosterUrl())
                genreOnPosterTextView.text = movie.genreIds.first()
                setFavoriteHeartIcon.setImageResource(R.drawable.ic_littel_yellow_heart_filled)
                setFavoriteHeartIcon.setOnClickListener {
                    onIconCLickListener?.invoke(movie)
                }
                itemView.setOnClickListener {
                    onItemClickListener?.invoke(movie)
                }
            }
        }
    }
}