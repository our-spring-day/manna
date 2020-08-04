package com.manna

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.manna.base.BaseFragment
import com.manna.databinding.FragmentFriendListBinding
import com.manna.model.Friend

class FriendListFragment :
    BaseFragment<FragmentFriendListBinding>(R.layout.fragment_friend_list) {
    private val friendList = arrayListOf(
        Friend("yeon_berry22", ""),
        Friend("heimish_08", ""),
        Friend("연재", ""),
        Friend("최우식", ""),
        Friend("유승호", ""),
        Friend("박서준", ""),
        Friend("임보라", ""),
        Friend("이지금", "")
    )
    private val friendAdapter = FriendAdapter(friendList)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdapter()
        binding.btnAdd.setOnClickListener {
            val intent = Intent(context, AddFriendActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setAdapter() {
        binding.rvFriendList.layoutManager = LinearLayoutManager(context)
        binding.rvFriendList.adapter = friendAdapter
    }

    companion object {
        fun newInstance() = FriendListFragment()
    }
}