package com.tuan88291.mvvmpattern.view.components

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.utils.Utils

class Screen(context: Context, val attrs: AttributeSet) : LinearLayout(context, attrs) {
    var onBackPress: (() -> Unit)? = null
    private var activity: AppCompatActivity? = null

    init {
        setUpView(context)
    }
    fun setUpView(context: Context) {
        if (!isInEditMode) {
            if (activity == null) {
                activity = context as AppCompatActivity
            }
        }
        this.orientation = VERTICAL
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Screen)
        val title = typedArray.getString(R.styleable.Screen_title)
        val leftIcon = typedArray.getDrawable(R.styleable.Screen_leftIcon)
        val header = Header(context, attrs)
        if (title != null) {
           header.setTitle(title)
        }
        if (leftIcon != null) {
            header.setLeftIcon(leftIcon)
        }
        header.setOnBackListener(object : HeaderListener {
            override fun onBackListener() {
                if (onBackPress != null) {
                    onBackPress?.invoke()
                } else {
                    Utils.backFragment(activity!!)
                }
            }
        })
        this.addView(header)
    }
}