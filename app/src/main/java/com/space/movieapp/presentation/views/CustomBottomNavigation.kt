package com.space.movieapp.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.button.MaterialButton
import com.space.movieapp.R
import com.space.movieapp.databinding.CustomBottomNavBinding

class CustomBottomNavigation @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: CustomBottomNavBinding =
        CustomBottomNavBinding.inflate(LayoutInflater.from(context), this, true)

    private var clickListener: BottomNavigationClickListener? = null

    init {
        setupButtonListeners()
    }

    fun setBottomNavigationClickListener(listener: BottomNavigationClickListener) {
        clickListener = listener
    }

    private fun setupButtonListeners() {
        binding.homeBottomButton.setOnClickListener {
            clickListener?.onHomeButtonClicked()
            //updateButtonStates(true)
        }

        binding.favoriteBottomButton.setOnClickListener {
            clickListener?.onFavoritesButtonClicked()
           // updateButtonStates(false)
        }
    }

    fun updateButtonStates(isHomeSelected: Boolean) {
        binding.homeBottomButton.updateButtonDesign(isHomeSelected)
        binding.favoriteBottomButton.updateButtonDesign(!isHomeSelected)
    }

    private fun MaterialButton.updateButtonDesign(isSelected: Boolean) {
        val buttonDesign = if (isSelected) selectedButtonDesign() else unSelectedButtonDesign()

        setBackgroundColor(buttonDesign.backgroundTint)
        setTextColor(buttonDesign.textColor)

        val drawableWrapper = DrawableCompat.wrap(compoundDrawables[0]).mutate()
        DrawableCompat.setTint(drawableWrapper, buttonDesign.iconColor)
        setCompoundDrawablesWithIntrinsicBounds(drawableWrapper, null, null, null)
    }

    private fun selectedButtonDesign(): ButtonColors {
        return ButtonColors(
            backgroundTint = ContextCompat.getColor(context, R.color.yellow_primary),
            textColor = ContextCompat.getColor(context, R.color.neutral_01_black),
            iconColor = ContextCompat.getColor(context, R.color.neutral_01_black)
        )
    }

    private fun unSelectedButtonDesign(): ButtonColors {
        return ButtonColors(
            backgroundTint = ContextCompat.getColor(context, R.color.neutral_02_darkest_grey),
            textColor = ContextCompat.getColor(context, R.color.neutral_08_whisper),
            iconColor = ContextCompat.getColor(context, R.color.neutral_08_whisper)
        )
    }

    private data class ButtonColors(
        val backgroundTint: Int,
        val textColor: Int,
        val iconColor: Int
    )
}

