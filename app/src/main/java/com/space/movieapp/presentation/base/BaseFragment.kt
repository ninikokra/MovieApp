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
import kotlin.reflect.KClass

typealias Inflater<VB> = (inflater: LayoutInflater, container: ViewGroup, attachToRoot: Boolean) -> VB

abstract class BaseFragment<VB : ViewBinding,VM: ViewModel> : Fragment() {

   /* protected val viewModel: VM by viewModelForClass(clazz = viewModelClass)
    abstract val viewModelClass: KClass<VM>*/

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    abstract fun inflate(): Inflater<VB>
    abstract fun onBind()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = this.inflate().invoke(inflater, container!!, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBind()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
