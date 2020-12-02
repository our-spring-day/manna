package com.manna.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.manna.R
import com.manna.databinding.FragmentCustomDialogBinding

class CustomDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentCustomDialogBinding
    private var heightRatio = 0.2403125f  // default
    private var widthRatio = 0.88888889f // default

    private var onClickListener: OnClickListener? = null

    interface OnClickListener {
        fun onClick(name: String)
    }

    override fun onStart() {
        super.onStart()
        val dpMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(dpMetrics)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        arguments?.getFloat(DIALOG_HEIGHT_RATIO)?.let {
            if (it > 0) {
                heightRatio = it
            }
        }
        arguments?.getFloat(DIALOG_WIDTH_RATIO)?.let {
            if (it > 0) {
                widthRatio = it
            }
        }
        dialog?.window?.setLayout(
            (dpMetrics.widthPixels * widthRatio).toInt(),
            (dpMetrics.heightPixels * heightRatio).toInt()
        )

        binding.btnOk.setOnClickListener {
            onClickListener?.onClick(binding.edtName.text.toString())
            dialog?.cancel()
        }

        binding.btnCancel.setOnClickListener {
            dialog?.cancel()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_custom_dialog, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    companion object {
        const val DIALOG_HEIGHT_RATIO = "DIALOG_HEIGHT_RATIO"
        const val DIALOG_WIDTH_RATIO = "DIALOG_WIDTH_RATIO"

        fun newInstance(listener: OnClickListener) = CustomDialogFragment().apply {
            onClickListener = listener
        }
    }
}