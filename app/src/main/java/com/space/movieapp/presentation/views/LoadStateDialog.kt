package com.space.movieapp.presentation.views

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.space.movieapp.R
import com.space.movieapp.databinding.LoadDataDesignBinding
import com.space.movieapp.utils.getColorCompat
import com.space.movieapp.utils.isVisible

/*
class LoadStateDialog @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding by lazy {
        LoadDataDesignBinding.inflate(LayoutInflater.from(context), this, true)
    }
    private lateinit var loadingErrorDialog: AlertDialog

     fun setupDialog(isError: Boolean) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(this)

        loadingErrorDialog = alertDialogBuilder.create()
        loadingErrorDialog.window?.setBackgroundDrawable(ColorDrawable(context.getColorCompat(R.color.neutral_01_black)))

        loadingErrorDialog.apply {
            with(binding) {
                if (isError) {
                    errorGroup.isVisible(true)
                } else {
                    errorGroup.isVisible(false)
                }
            }

            loadingErrorDialog.show()
        }
    }
    fun setRefreshButton(listener: () -> Unit) {
        binding.refreshButton.setOnClickListener {
            listener.invoke()
        }
    }
    fun dialogDismiss(){
        loadingErrorDialog.dismiss()
    }
}*/
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
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
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

