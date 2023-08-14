package com.space.movieapp.utils

import com.space.movieapp.data.remote.network_utils.NetworkKeys
import com.space.movieapp.domain.model.MoviesDomainModel

/**
 * getFormattedReleaseDate is to get the formatted release date
 * by dropping the last 6 characters from the original release date.
 */

fun MoviesDomainModel.ResultDomain.getFormattedReleaseDate(): String {
    return releaseDate.dropLast(6)
}

/**
 * getFullPosterUrl is to get the full poster URL by combining
 * the base image URL with the poster path.
 */

fun MoviesDomainModel.ResultDomain.getFullPosterUrl(): String {
    return NetworkKeys.IMAGE_URL + posterPath
}