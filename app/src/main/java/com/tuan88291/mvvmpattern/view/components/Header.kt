package com.tuan88291.mvvmpattern.view.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.databinding.HeaderComponentBinding

interface HeaderListener {
    fun onBackListener()
}
class Header(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {
    private var binding: HeaderComponentBinding? = null
    private var backListener: HeaderListener? = null
    init {
        setUpView(context)
    }
    fun setTitle(title: String) {
        binding?.title?.text = title
    }
    fun setLeftIcon(leftIcon: Drawable?) {
        binding?.toolbar?.setNavigationIcon(leftIcon!!)
    }
    fun setOnBackListener(listener: HeaderListener) {
        this.backListener = listener
    }
    fun setUpView(context: Context) {
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.header_component, this, true)
        }

        binding?.toolbar?.setNavigationIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.baseline_arrow_back_24, null))
        binding?.toolbar?.setNavigationOnClickListener {
            if (backListener != null) {
                backListener?.onBackListener()
            }
        }
    }
}