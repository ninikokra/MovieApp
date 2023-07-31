package com.space.movieapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.space.movieapp.utils.lifecycleScope
import com.space.movieapp.utils.navigation.NavigationCommand
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass

typealias Inflater<VB> = (inflater: LayoutInflater, container: ViewGroup, attachToRoot: Boolean) -> VB

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    protected val viewModel: VM by viewModelForClass(clazz = viewModelClass)
    abstract val viewModelClass: KClass<VM>

    protected abstract val layout: Int

    abstract fun onBind()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigation()
        onBind()
    }

    private fun observeNavigation() {
        lifecycleScope {
            viewModel.navigation.collect { navigationCommand ->
                handleNavigation(navigationCommand)
            }
        }
    }

    private fun handleNavigation(navCommand: NavigationCommand) {
        when (navCommand) {
            is NavigationCommand.ToDirection -> findNavController().navigate(navCommand.directions)
            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }
}
