package com.manna.ui

import android.os.Bundle
import com.manna.R
import com.manna.base.BaseActivity
import com.manna.databinding.ActivityMainBinding
import com.manna.ui.friend.FriendListFragment
import com.manna.ui.friend.FriendRequestFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnFriendList.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.frame,
                    FriendListFragment.newInstance()
                )
                .commit()
        }

        binding.btnFriendRequest.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.frame,
                    FriendRequestFragment.newInstance()
                )
                .commit()
        }
    }
}
