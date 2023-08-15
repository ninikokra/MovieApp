package com.space.movieapp.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.space.movieapp.R
import java.util.concurrent.TimeUnit

fun View.isVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun Context.getColorCompat(@ColorRes colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}

fun ImageView.setImage(url: String?) {
    Glide.with(this).load(url).into(this)
}
fun Double.formatOneDecimal(): String {
    return String.format("%.1f", this)
}

fun Int.timeFormatter(): String {
    val hours = TimeUnit.MINUTES.toHours(this.toLong())
    val remainMinutes = this - TimeUnit.HOURS.toMinutes(hours)
    return String.format("%dh %dm", hours, remainMinutes)
}

fun ImageView.setImageDrawableResource(@DrawableRes drawableResId: Int) {
    val drawable = ContextCompat.getDrawable(context, drawableResId)
    setImageDrawable(drawable)
}

fun View.getStringRes(@StringRes resId: Int, vararg formatArgs: Any?): String {
    return context.getString(resId, *formatArgs)
}

fun ImageView.toggleFavoriteHeartIcons() {
    if (tag == null) {
        setImageDrawableResource(R.drawable.ic_littel_yellow_heart_filled)
        tag = R.drawable.ic_little_yellow_heart_shape
    } else {
        val currentDrawableResId = tag as Int
        val newDrawableResId =
            if (currentDrawableResId == R.drawable.ic_littel_yellow_heart_filled) {
                R.drawable.ic_little_yellow_heart_shape
            } else {
                R.drawable.ic_littel_yellow_heart_filled
            }
        setImageDrawableResource(newDrawableResId)
        tag = newDrawableResId
    }
}