package com.tuan88291.mvvmpattern.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.databinding.HeaderComponentBinding
import com.tuan88291.mvvmpattern.utils.Utils

class Header(context: Context, val attrs: AttributeSet) : RelativeLayout(context, attrs) {
    private var binding: HeaderComponentBinding? = null
    private var activity: AppCompatActivity? = null
    var onBackPress: (() -> Unit)? = null

    init {
        setUpView(context)
    }
    fun setUpView(context: Context) {
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.header_component, this, true)
        }
        if (activity == null) {
            activity = context as AppCompatActivity
        }
        binding?.toolbar?.setNavigationIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.baseline_arrow_back_24, null))
        binding?.toolbar?.setNavigationOnClickListener {
            if (onBackPress != null) {
                onBackPress?.invoke()
            } else {
                Utils.backFragment(activity!!)
            }
        }
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Header)
        val title = typedArray.getString(R.styleable.Header_title)
        val leftIcon = typedArray.getDrawable(R.styleable.Header_leftIcon)
        if (title != null) {
            binding?.title?.text = title
        }
        if (leftIcon != null) {
            binding?.toolbar?.setNavigationIcon(leftIcon)
        }
    }
}