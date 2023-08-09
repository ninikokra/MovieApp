package com.space.movieapp.presentation.views

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.chip.Chip
import com.space.movieapp.R
import com.space.movieapp.databinding.CustomSearchBarBinding
import com.space.movieapp.utils.isVisible

class CustomSearchBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding by lazy {
        CustomSearchBarBinding.inflate(LayoutInflater.from(context), this, true)
    }
    private var isSearchButtonActive = false

    init {
        setupSearchBarEditText()
        setupSearchSelectionButton()
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

                genresChipGroup.isVisible(isSearchButtonActive)
                    searchSelectionButton.setBackgroundResource(if (isSearchButtonActive){
                        R.drawable.ic_selected_search
                    } else{
                        R.drawable.ic_unselected_search
                    })

              /*  if (isSearchButtonActive) {
                    searchSelectionButton.setBackgroundResource(R.drawable.ic_selected_search)
                    genresChipGroup.isVisible(true)
                } else {
                    searchSelectionButton.setBackgroundResource(R.drawable.ic_unselected_search)
                    genresChipGroup.isVisible(false)
                }*/
            }
        }
    }

    fun setPopularMoviesChipClickListener(listener: () -> Unit) {
        binding.popularMoviesChip.setOnClickListener {
            listener.invoke()
        }
    }

    fun setTopRatedMoviesChipClickListener(listener: () -> Unit) {
        binding.topRatedMoviesChip.setOnClickListener {
            listener.invoke()
        }
    }
}