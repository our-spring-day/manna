package com.manna.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.manna.R
import com.manna.databinding.ItemSelectedFriendBinding
import com.manna.glide
import com.manna.model.Friend

class SelectedFriendAdapter() :
    RecyclerView.Adapter<SelectedFriendAdapter.SelectedFriendViewHolder>() {
    private val items = mutableListOf<Friend>()
    private lateinit var binding: ItemSelectedFriendBinding
    private var onClickListener: OnClickListener? = null

    interface OnClickListener {
        fun onClick(friend: Friend)
    }

    fun setOnClickListener(listener: OnClickListener) {
        onClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedFriendViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_selected_friend,
            parent,
            false
        )

        return SelectedFriendViewHolder(binding)
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: SelectedFriendViewHolder, position: Int) =
        holder.bind(items[position], onClickListener)

    fun addData(friend: Friend) {
        //items.clear()
        items.add(friend)
        notifyDataSetChanged()
    }

    fun removeData(friend: Friend) {
        val position = items.indexOf(friend)
        items.remove(friend)
        notifyItemRemoved(position)
    }

    class SelectedFriendViewHolder(private val binding: ItemSelectedFriendBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun bind(item: Friend, listener: OnClickListener?) {
            binding.run {
                itemView.run {
                    setOnClickListener {
                        listener?.onClick(item)
                    }
                }
                ivImage.glide(item.image)
            }
        }
    }
}