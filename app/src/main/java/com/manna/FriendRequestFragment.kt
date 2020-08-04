package com.manna

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.manna.base.BaseFragment
import com.manna.databinding.FragmentFriendRequestBinding
import com.manna.model.FriendRequest

class FriendRequestFragment :
    BaseFragment<FragmentFriendRequestBinding>(R.layout.fragment_friend_request) {
    private val friendRequestList = arrayListOf(
        FriendRequest("yeon_berry22", "최우식", "5", ""),
        FriendRequest("heimish_08", "이연재", "5", "")
    )
    private val friendRequestAdapter = FriendRequestAdapter(friendRequestList)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdapter()
        friendRequestAdapter.setConfirmButtonListener(object :
            FriendRequestAdapter.ConfirmButtonListener {
            override fun onClick() {

            }
        })
        friendRequestAdapter.setRefuseButtonListener(object :
            FriendRequestAdapter.RefuseButtonListener {
            override fun showDialog() {
                val newFragment =
                    MultiDialogFragment.newInstance(
                        getString(R.string.msg_refuse), object :
                            MultiDialogFragment.OnClickListener {
                            override fun onClick() {

                            }
                        }
                    )
                newFragment.show(requireActivity().supportFragmentManager, "dialog")
            }
        })
        friendRequestAdapter.setCancelButtonListener(object :
            FriendRequestAdapter.CancelButtonListener {
            override fun onClick() {

            }

        })
    }

    private fun setAdapter() {
        binding.rvFriendRequest.layoutManager = LinearLayoutManager(context)
        binding.rvFriendRequest.adapter = friendRequestAdapter
    }

    companion object {
        fun newInstance() = FriendRequestFragment()
    }
}