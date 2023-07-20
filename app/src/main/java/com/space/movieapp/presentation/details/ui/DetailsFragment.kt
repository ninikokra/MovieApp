package com.space.movieapp.presentation.details.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.space.movieapp.R
import com.space.movieapp.databinding.FragmentDetailsBinding
import com.space.movieapp.presentation.base.BaseFragment
import com.space.movieapp.presentation.base.Inflater
import com.space.movieapp.presentation.details.vm.DetailsViewModel

class DetailsFragment : BaseFragment<FragmentDetailsBinding,DetailsViewModel>() {

    override fun inflate(): Inflater<FragmentDetailsBinding> {
        return FragmentDetailsBinding::inflate
    }

    override fun onBind() {
        naviagateHome()
    }

    fun naviagateHome(){
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_homeFragment)
        }

    }
}