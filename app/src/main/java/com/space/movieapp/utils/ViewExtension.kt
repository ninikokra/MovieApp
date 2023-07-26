package com.space.movieapp.utils

import android.content.Context
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun View.isVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}
fun Context.getColorCompat(@ColorRes colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}