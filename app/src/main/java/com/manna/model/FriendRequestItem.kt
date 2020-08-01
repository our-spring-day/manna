package com.manna.model

import java.io.Serializable

data class FriendRequestItem(
    val nickname: String,
    val acquaintance: String,
    val image: String
) : Serializable