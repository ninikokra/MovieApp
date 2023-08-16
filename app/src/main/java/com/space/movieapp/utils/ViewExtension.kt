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
//View visibility Extension
fun View.isVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}
//getColorCompat Extension
fun Context.getColorCompat(@ColorRes colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}

//glide image loader extension, loads an image from the provided URL
fun ImageView.setImage(url: String?) {
    Glide.with(this).load(url).into(this)
}
//this formatOneDecimal i need to format double for one decimal place
fun Double.formatOneDecimal(): String {
    return String.format("%.1f", this)
}
//this extension formats an integer value representing time in minutes into a formatted string of hours and minutes.
fun Int.timeFormatter(): String {
    val hours = TimeUnit.MINUTES.toHours(this.toLong())
    val remainMinutes = this - TimeUnit.HOURS.toMinutes(hours)
    return String.format("%dh %dm", hours, remainMinutes)
}
//setDrawableResource extension
fun ImageView.setImageDrawableResource(@DrawableRes drawableResId: Int) {
    val drawable = ContextCompat.getDrawable(context, drawableResId)
    setImageDrawable(drawable)
}
//string resource extension
fun View.getStringRes(@StringRes resId: Int, vararg formatArgs: Any?): String {
    return context.getString(resId, *formatArgs)
}
fun ImageView.toggleFavoriteHeartIcons(isFavorite: Boolean) {
    if (isFavorite) {
        setImageDrawableResource(R.drawable.ic_littel_yellow_heart_filled)
    } else {
        setImageDrawableResource(R.drawable.ic_little_yellow_heart_shape)
    }
}
fun ImageView.updateFavoriteIcon(isFavorite: Boolean) {
    if (isFavorite) {
        setImageDrawableResource(R.drawable.ic_yello_hearts_filled_big)
    } else {
        setImageDrawableResource(R.drawable.ic_yellow_heart_big)
    }
}

