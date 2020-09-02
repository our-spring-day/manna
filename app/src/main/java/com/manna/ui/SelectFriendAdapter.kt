package com.manna.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.manna.R
import com.manna.databinding.ItemSelectFriendBinding
import com.manna.glide
import com.manna.model.Friend

class SelectFriendAdapter(private var items: MutableList<Friend>) :
    RecyclerView.Adapter<SelectFriendAdapter.SelectFriendViewHolder>() {
    //private val items = mutableListOf<Friend>()
    private lateinit var binding: ItemSelectFriendBinding
    private var onClickListener: OnClickListener? = null
    private val list = mutableListOf<Friend>()

    init {
        list.addAll(items)
    }

    interface OnClickListener {
        fun onClick(friend: Friend, isChecked: Boolean)
    }

    fun setOnClickListener(listener: OnClickListener) {
        onClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectFriendViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_select_friend,
            parent,
            false
        )

        return SelectFriendViewHolder(binding)
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: SelectFriendViewHolder, position: Int) =
        holder.bind(items[position], onClickListener)

    fun updateList(text: String) {
        items.clear()
        if (text.isEmpty()) {
            items.addAll(list)
        } else {
            for (i in 0 until list.size) {
                if (list[i].nickname.toLowerCase().contains(text)) {
                    items.add(list[i])
                }
            }
        }
        notifyDataSetChanged()
    }

    class SelectFriendViewHolder(private val binding: ItemSelectFriendBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun bind(item: Friend, listener: OnClickListener?) {
            binding.run {
                itemView.run {
                    cbFriend.setOnClickListener {
                        listener?.onClick(item, cbFriend.isChecked)
                    }
                }
                tvNickname.text = item.nickname
                ivImage.glide(item.image)
            }
        }
    }
}