package com.manna.ui.friend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.manna.R
import com.manna.databinding.ItemFriendRequestBinding
import com.manna.model.FriendRequest


class FriendRequestAdapter(private val items: MutableList<FriendRequest>) :
    RecyclerView.Adapter<FriendRequestAdapter.FriendRequestViewHolder>() {
    //private val items = mutableListOf<FriendRequest>()
    private lateinit var binding: ItemFriendRequestBinding
    private lateinit var confirmButtonListener: ConfirmButtonListener
    private lateinit var refuseButtonListener: RefuseButtonListener
    private lateinit var cancelButtonListener: CancelButtonListener

    interface ConfirmButtonListener {
        fun onClick()
    }

    interface RefuseButtonListener {
        fun showDialog()
    }

    interface CancelButtonListener {
        fun onClick()
    }

    fun setConfirmButtonListener(listener: ConfirmButtonListener) {
        confirmButtonListener = listener
    }

    fun setRefuseButtonListener(listener: RefuseButtonListener) {
        refuseButtonListener = listener
    }

    fun setCancelButtonListener(listener: CancelButtonListener) {
        cancelButtonListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendRequestViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_friend_request,
            parent,
            false
        )

        return FriendRequestViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: FriendRequestViewHolder, position: Int) {
        if (::refuseButtonListener.isInitialized) {
            holder.bind(
                items[position],
                confirmButtonListener,
                refuseButtonListener,
                cancelButtonListener
            )
        }
    }

    fun addData(addDataList: List<FriendRequest>) {
        items.clear()
        items.addAll(addDataList)
        notifyDataSetChanged()
    }

    class FriendRequestViewHolder(private val binding: ItemFriendRequestBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun bind(
            item: FriendRequest,
            confirmButtonListener: ConfirmButtonListener,
            refuseButtonListener: RefuseButtonListener,
            cancelButtonListener: CancelButtonListener
        ) {
            binding.run {
                tvNickname.text = item.nickname
                tvFriend.text = item.friendName + "님 외 " + item.friendNum + "명과 친구입니다"
                btnConfirm.setOnClickListener {
                    btnConfirm.visibility = View.INVISIBLE
                    btnRefuse.visibility = View.INVISIBLE
                    btnCancel.visibility = View.VISIBLE
                    confirmButtonListener.onClick()
                }
                btnRefuse.setOnClickListener {
                    refuseButtonListener.showDialog()
                }
                btnCancel.setOnClickListener {
                    cancelButtonListener.onClick()
                }
            }
        }
    }
}