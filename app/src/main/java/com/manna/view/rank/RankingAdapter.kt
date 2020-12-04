package com.manna.view.rank

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import com.manna.view.User

class RankingAdapter :
    ListAdapter<String, RankingViewHolder>(
        object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem
        }
    ) {
    private var onClickListener: OnClickListener? = null

    interface OnClickListener {
        fun onClick(user: String)
    }

    fun setOnClickListener(listener: OnClickListener) {
        onClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder =
        RankingViewHolder.OngoingViewHolder(parent)

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        (holder as RankingViewHolder.OngoingViewHolder).bind(currentList[position], onClickListener)
    }

}
