package com.manna

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.manna.databinding.ItemFriendListBinding
import com.manna.model.Friend

class FriendAdapter(private val items: MutableList<Friend>) :
    RecyclerView.Adapter<FriendAdapter.FriendViewHolder>() {
    //private val items = mutableListOf<Friend>()
    private lateinit var binding: ItemFriendListBinding
    private var onClickListener: OnClickListener? = null

    interface OnClickListener {
        fun onClick(friend: Friend)
    }

    fun setOnClickListener(listener: OnClickListener) {
        onClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_friend_list,
            parent,
            false
        )

        return FriendViewHolder(binding)
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) =
        holder.bind(items[position], onClickListener)

    fun addData(addDataList: List<Friend>) {
        items.clear()
        items.addAll(addDataList)
        notifyDataSetChanged()
    }

    class FriendViewHolder(private val binding: ItemFriendListBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: Friend, listener: OnClickListener?) {
            binding.run {
                itemView.run {
                    setOnClickListener {
                        listener?.onClick(item)
                    }
                }
                tvNickname.text = item.nickname
            }
        }
    }
}