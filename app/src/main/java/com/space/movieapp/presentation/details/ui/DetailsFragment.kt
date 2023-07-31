package com.space.movieapp.presentation.details.ui

import androidx.navigation.fragment.findNavController
import com.space.movieapp.R
import com.space.movieapp.databinding.FragmentDetailsBinding
import com.space.movieapp.presentation.base.BaseFragment
import com.space.movieapp.presentation.details.vm.DetailsViewModel
import com.space.movieapp.utils.viewBinding
import kotlin.reflect.KClass

class DetailsFragment : BaseFragment<DetailsViewModel>() {

    override val viewModelClass: KClass<DetailsViewModel>
        get() = DetailsViewModel::class

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_details

    override fun onBind() {
        naviagateHome()
    }

    private fun naviagateHome() {
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_homeFragment)
        }
    }
}