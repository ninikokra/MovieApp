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
import com.space.movieapp.utils.getColorCompat

class CustomBottomNavigation @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding by lazy {
        CustomBottomNavBinding.inflate(LayoutInflater.from(context), this, true)
    }
    private var onHomeButtonClicked: (() -> Unit)? = null
    private var onFavoritesButtonClicked: (() -> Unit)? = null

    init {
        setupButtonListeners()
    }

    fun setOnHomeButtonClicked(listener: () -> Unit) {
        onHomeButtonClicked = listener
    }

    fun setOnFavoritesButtonClicked(listener: () -> Unit) {
        onFavoritesButtonClicked = listener
    }

    private fun setupButtonListeners() {
        with(binding) {
            homeBottomButton.setOnClickListener {
                onHomeButtonClicked?.invoke()
                updateButtonStates(true)
            }
            favoriteBottomButton.setOnClickListener {
                onFavoritesButtonClicked?.invoke()
                updateButtonStates(false)
            }
        }
    }

    fun updateButtonStates(isHomeSelected: Boolean) {
        with(binding){
            homeBottomButton.updateButtonDesign(isHomeSelected)
            favoriteBottomButton.updateButtonDesign(!isHomeSelected)
        }
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
            backgroundTint = context.getColorCompat(R.color.yellow_primary),
            textColor = context.getColorCompat(R.color.neutral_01_black),
            iconColor = context.getColorCompat(R.color.neutral_01_black)
        )
    }

    private fun unSelectedButtonDesign(): ButtonColors {
        return ButtonColors(
            backgroundTint = context.getColorCompat(R.color.neutral_02_darkest_grey),
            textColor = context.getColorCompat(R.color.neutral_08_whisper),
            iconColor = context.getColorCompat(R.color.neutral_08_whisper)
        )
    }
}