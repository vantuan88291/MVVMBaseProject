package com.tuan88291.mvvmpattern.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.databinding.BottomMenuBinding

class BottomMenu(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {
    private var binding: BottomMenuBinding? = null
    init {
        setUpView(context)
    }
    fun setUpView(context: Context) {
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.bottom_menu, this, true)
        }
    }
}