package com.space.movieapp.presentation.views

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.space.movieapp.R
import com.space.movieapp.databinding.CustomSearchBarBinding
import com.space.movieapp.utils.isVisible

class CustomSearchBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: CustomSearchBarBinding =
        CustomSearchBarBinding.inflate(LayoutInflater.from(context), this, true)

    private var isSearchButtonActive = false
    private var isTopRatedSelected = false
    private var isPopularSelected = false

    init {
        setupSearchBarEditText()
        setupSearchSelectionButton()
        setTopRateMovies()
        setPopularMovies()
    }

    private fun setupSearchBarEditText() {
        binding.searchBarEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.searchSelectionButton.setBackgroundResource(R.drawable.ic_cancel)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupSearchSelectionButton() {
        with(binding) {
            searchSelectionButton.setOnClickListener {
                isSearchButtonActive = !isSearchButtonActive

                if (isSearchButtonActive) {
                    searchSelectionButton.setBackgroundResource(R.drawable.ic_selected_search)
                    genresBox.isVisible(true)
                } else {
                    searchSelectionButton.setBackgroundResource(R.drawable.ic_unselected_search)
                    genresBox.isVisible(false)
                }
            }
        }
    }

    private fun setTopRateMovies() {
        binding.topRatedMoviesTextView.setOnClickListener {
            if (!isTopRatedSelected) {

                isTopRatedSelected = true
                isPopularSelected = false

                updateSelectedGenreDesign()
            }
        }
    }

    private fun setPopularMovies() {
        binding.popularMoviesTextView.setOnClickListener {
            if (!isPopularSelected) {

                isPopularSelected = true
                isTopRatedSelected = false

                updateSelectedGenreDesign()
            }
        }
    }

    private fun updateSelectedGenreDesign() {
        with(binding) {
            topRatedMoviesTextView.setBackgroundResource(if (isTopRatedSelected) R.drawable.text_shape_yellow else R.drawable.text_shape)
            topRatedMoviesTextView.setTextColor(
                ContextCompat.getColor(
                    context,
                    if (isTopRatedSelected) R.color.neutral_01_black else R.color.neutral_08_whisper
                )
            )
            popularMoviesTextView.setBackgroundResource(if (isPopularSelected) R.drawable.text_shape_yellow else R.drawable.text_shape)
            popularMoviesTextView.setTextColor(
                ContextCompat.getColor(
                    context,
                    if (isPopularSelected) R.color.neutral_01_black else R.color.neutral_08_whisper
                )
            )
        }
    }

}
