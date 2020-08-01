package com.manna.model

import java.io.Serializable

data class FriendRequest(
    val nickname: String,
    val friendName: String,
    val friendNum: String,
    val image: String
) : Serializable