package com.manna.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.manna.MainActivity
import com.manna.R
import com.manna.base.BaseFragment
import com.manna.databinding.FragmentAddMeetBinding

class AddMeetFragment :
    BaseFragment<FragmentAddMeetBinding>(R.layout.fragment_add_meet) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnComplete.setOnClickListener {
            (activity as? MainActivity)?.replace(SelectFriendFragment(), true)
        }

        binding.btnClear.setOnClickListener {
            binding.edtTitle.text.clear()
        }

        binding.edtTitle.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.edtTitle.text.isNotEmpty()) {
                    binding.btnClear.visibility = View.VISIBLE
                } else {
                    binding.btnClear.visibility = View.GONE
                }
            }

        })
    }

    companion object {
        fun newInstance() = AddMeetFragment()
    }
}
