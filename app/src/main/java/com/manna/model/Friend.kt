package com.manna.model

import java.io.Serializable

data class Friend(
    val nickname: String,
    val image: Int
) : Serializable