package com.manna.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.manna.R
import com.manna.util.ViewUtil

class ProfileImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    private var cornerRadius: Float = 0f,
    private var borderDrawable: Drawable? = null
) : ConstraintLayout(context, attrs, defStyle) {

    init {
        inflate(context, R.layout.view_profile_image, this)

        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.ProfileImageView)
            cornerRadius = a.getDimension(R.styleable.ProfileImageView_corner_radius, 0f)
            borderDrawable = a.getDrawable(R.styleable.ProfileImageView_border_res)

            if (borderDrawable == null) {
                borderDrawable = GradientDrawable().apply {
                    this.cornerRadius = this@ProfileImageView.cornerRadius
                    this.setStroke(ViewUtil.convertDpToPixel(context, 2f).toInt(), ContextCompat.getColor(context, R.color.ffdadada))
                }
            }
            a.recycle()
        }

        borderDrawable?.let {
            findViewById<View>(R.id.border).background = it
        }
    }

    fun setImage(imageUrl: String) {
        setImageUrl(imageUrl)
    }

    private fun setImageUrl(imageUrl: String) {
        val imageView = findViewById<ImageView>(R.id.image)

        Glide.with(context)
            .applyDefaultRequestOptions(
                RequestOptions.bitmapTransform(
                    RoundedCorners(
                        ViewUtil.convertDpToPixel(context, cornerRadius).toInt()
                    )
                )
            )
            .load(imageUrl)
            .override(this.width, this.height)
            .into(imageView)
    }
}