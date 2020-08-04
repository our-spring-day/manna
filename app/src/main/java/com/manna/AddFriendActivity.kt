package com.manna

import android.os.Bundle
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
    }
}
