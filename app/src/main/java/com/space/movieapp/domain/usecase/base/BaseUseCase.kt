package com.space.movieapp.domain.usecase.base

abstract class BaseUseCase<in Params, out T> {
    abstract suspend operator fun invoke(params: Params? = null): T
}