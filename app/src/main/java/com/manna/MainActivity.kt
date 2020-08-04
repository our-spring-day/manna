package com.manna

import android.os.Bundle
import com.manna.base.BaseActivity
import com.manna.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnFriendList.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, FriendListFragment.newInstance())
                .commit()
        }

        binding.btnFriendRequest.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, FriendRequestFragment.newInstance())
                .commit()
        }
    }
}
