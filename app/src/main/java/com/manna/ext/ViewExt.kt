package com.manna.ext

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.manna.R
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("android:visibleIf")
fun View.setVisibleIf(value: Boolean) {
    isVisible = value
}

@BindingAdapter("android:invisibleIf")
fun View.setInvisibleIf(value: Boolean) {
    isInvisible = value
}

@BindingAdapter("android:goneIf")
fun View.setGoneIf(value: Boolean) {
    isGone = value
}

@BindingAdapter("textViewBackground")
fun TextView.setBackground(isClick: Boolean) {
    background = if (isClick) {
        ContextCompat.getDrawable(context, R.drawable.bg_tv_blue)
    } else {
        ContextCompat.getDrawable(context, R.drawable.bg_tv_gray)
    }
}

@BindingAdapter("textColor")
fun TextView.setColor(isClick: Boolean) {
    if (isClick) {
        setTextColor(ContextCompat.getColor(context, R.color.keyColor))
    } else {
        setTextColor(ContextCompat.getColor(context, R.color.black))
    }
}

@BindingAdapter("date")
fun TextView.setDate(timeStamp: Long?) {
    if (timeStamp != null) {
        text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(timeStamp))
    }
}

fun View.openKeyboard() {
    requestFocus()
    val imm = context
        .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.closeKeyboard() {
    if (context != null) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}