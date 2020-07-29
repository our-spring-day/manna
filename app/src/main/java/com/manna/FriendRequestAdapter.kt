package com.manna

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.manna.databinding.ItemFriendRequestBinding
import com.manna.model.FriendRequest


class FriendRequestAdapter(private val items : MutableList<FriendRequest>) : RecyclerView.Adapter<FriendRequestAdapter.FriendRequestViewHolder>() {
    //private val items = mutableListOf<FriendRequest>()
    private lateinit var binding: ItemFriendRequestBinding
    private lateinit var listener: OnClickListener

    interface OnClickListener {
        fun onClick(friendRequest: FriendRequest)
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendRequestViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_friend_request,
            parent,
            false
        )

        return FriendRequestViewHolder(binding)
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: FriendRequestViewHolder, position: Int) =
        holder.bind(items[position], listener)

    fun addData(addDataList: List<FriendRequest>) {
        items.clear()
        items.addAll(addDataList)
        notifyDataSetChanged()
    }

    class FriendRequestViewHolder(private val binding: ItemFriendRequestBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: FriendRequest, listener: OnClickListener?) {
            binding.run {
                itemView.run {
                    setOnClickListener {
                        listener?.onClick(item)
                    }
                }
                ivImage
                tvNickname.text = item.nickname
                tvFriend.text = item.friendName + "님 외 " + item.friendNum +"명과 친구입니다"
            }
        }
    }
}