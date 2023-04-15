package com.tuan88291.mvvmpattern.view.components

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.tuan88291.mvvmpattern.R

class ImageWrap(context: Context, val attrs: AttributeSet?) : AppCompatImageView(context, attrs) {
    init {
        setUpImageUrl()
    }
    private fun setUpImageUrl() {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageWrap)
        val url = typedArray.getString(R.styleable.ImageWrap_url)
        val centerCrop = typedArray.getBoolean(R.styleable.ImageWrap_centerCrop, false)
        val centerInside = typedArray.getBoolean(R.styleable.ImageWrap_centerCrop, false)
        val fitCenter = typedArray.getBoolean(R.styleable.ImageWrap_centerCrop, false)
        val circle = typedArray.getBoolean(R.styleable.ImageWrap_circle, false)
        if (url != null) {
            var glide: RequestBuilder<Drawable>
            if (circle) {
                glide = Glide.with(this)
                    .load(url)
                    .circleCrop()
                    .placeholder(ColorDrawable(Color.GRAY))
                    .error(ColorDrawable(Color.RED))
            } else {
                glide = Glide.with(this)
                    .load(url)
                    .placeholder(ColorDrawable(Color.GRAY))
                    .error(ColorDrawable(Color.RED))
            }
            if (centerCrop) {
                glide.centerCrop().into(this)
            } else if (centerInside) {
                glide.centerInside().into(this)
            } else if (fitCenter) {
                glide.fitCenter().into(this)
            } else {
                glide.into(this)
            }
        }
    }
}