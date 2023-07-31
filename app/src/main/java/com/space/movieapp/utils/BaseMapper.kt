package com.space.movieapp.utils

interface BaseMapper<in MODEL_A, out MODEL_B> {
    operator fun invoke(model: MODEL_A): MODEL_B
}