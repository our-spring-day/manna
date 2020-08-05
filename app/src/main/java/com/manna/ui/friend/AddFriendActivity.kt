package com.manna.ui.friend

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.manna.R
import com.manna.base.BaseActivity
import com.manna.databinding.ActivityAddFriendBinding

class AddFriendActivity : BaseActivity<ActivityAddFriendBinding>(R.layout.activity_add_friend) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnClear.setOnClickListener {
            binding.edtFriendId.text.clear()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnConfirm.setOnClickListener {
            binding.clFriend.visibility = View.VISIBLE
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding.edtFriendId.windowToken, 0)
        }

        binding.edtFriendId.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.edtFriendId.text.isNotEmpty()) {
                    binding.btnClear.visibility = View.VISIBLE
                } else {
                    binding.btnClear.visibility = View.GONE
                }
            }

        })
    }
}
