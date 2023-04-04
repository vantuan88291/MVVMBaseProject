package com.tuan88291.mvvmpattern.view.components

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.tuan88291.mvvmpattern.R


class FontIcon(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    init {
        setupFont()
    }
    fun setupFont() {
        setTypeface(ResourcesCompat.getFont(context!!, R.font.fontawesome))
    }
}