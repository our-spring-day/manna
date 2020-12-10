package com.manna.presentation.rank

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.manna.R
import com.manna.databinding.FragmentUrgingBinding
import com.manna.util.Logger
import com.manna.util.ViewUtil

class UrgingBottomFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentUrgingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_urging, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        resources.getStringArray(R.array.urging_messages).forEach { message ->
            val messageView = LayoutInflater.from(requireContext())
                .inflate(R.layout.view_urging_message, binding.root as ViewGroup, false) as TextView
            messageView.text = message

            binding.urgingMessageLayout.addView(messageView)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)


        Logger.d("${ViewUtil.getScreenWidthPixels(requireContext())}")

        if (ViewUtil.getScreenWidthPixels(requireContext()) > 640f) {
            binding.urgingMessageLayout.updateLayoutParams<ViewGroup.LayoutParams> {
                width = ViewUtil.getScreenWidthPixels(requireContext())
            }
        }
    }


    companion object {
        fun newInstance() =
            UrgingBottomFragment()
    }

}