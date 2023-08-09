package com.space.movieapp.presentation.views

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.space.movieapp.databinding.LoadDataDesignBinding
import com.space.movieapp.utils.isVisible
import android.app.Dialog
import android.graphics.Color
import android.view.ViewGroup
import android.view.WindowManager

class LoadStateDialog(context: Context) : Dialog(context) {

    private val binding: LoadDataDesignBinding =
        LoadDataDesignBinding.inflate(LayoutInflater.from(context))

    init {
        setContentView(binding.root)
        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

    fun showProgressBar() {
        with(binding) {
            progressBar.isVisible(true)
            errorGroup.isVisible(false)
        }
        show()
    }

    fun showErrorDialog() {
        with(binding) {
            progressBar.isVisible(false)
            errorGroup.isVisible(true)
        }
        show()
    }

    fun setRefreshButton(listener: () -> Unit) {
        binding.refreshButton.setOnClickListener {
            dismiss()
            listener.invoke()
        }
    }
}

