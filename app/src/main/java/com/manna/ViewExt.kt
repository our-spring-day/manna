package com.manna

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.glide(image: Int) {
    Glide.with(context)
        .load(image)
        .into(this)
}