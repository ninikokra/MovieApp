package com.space.movieapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.space.movieapp.utils.navigation.NavigationCommand
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import com.space.movieapp.utils.viewModelScope

abstract class BaseViewModel : ViewModel() {

    private val _navigation = MutableSharedFlow<NavigationCommand>()
    val navigation: SharedFlow<NavigationCommand> get() = _navigation

    fun navigate(navDirections: NavDirections) {
        viewModelScope {
            _navigation.emit(NavigationCommand.ToDirection(navDirections))
        }
    }

    fun navigateBack() {
        viewModelScope {
            _navigation.emit(NavigationCommand.Back)
        }
    }
}