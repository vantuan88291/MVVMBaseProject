package com.tuan88291.mvvmpattern.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.databinding.BottomMenuBinding

enum class MenuType {
    home, map, route, notification, add
}
class BottomMenu(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {
    private var binding: BottomMenuBinding? = null
    var onChangeMenu: ((menu: MenuType) -> Unit)? = null

    init {
        setUpView(context)
    }
    fun setUpView(context: Context) {
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.bottom_menu, this, true)
        }
        binding?.linearLayout?.setOnClickListener {
            onChangeMenu?.invoke(MenuType.home)
            binding?.let {
                it.icon.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorWhite))
                it.icon1.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGrey))
                it.icon2.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGrey))
                it.icon3.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGrey))

                it.label.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite))
                it.label1.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey))
                it.label2.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey))
                it.label3.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey))
            }
        }

        binding?.linearLayout1?.setOnClickListener {
            onChangeMenu?.invoke(MenuType.map)
            binding?.let {
                it.icon1.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorWhite))
                it.icon.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGrey))
                it.icon2.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGrey))
                it.icon3.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGrey))

                it.label1.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite))
                it.label.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey))
                it.label2.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey))
                it.label3.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey))
            }
        }

        binding?.linearLayout2?.setOnClickListener {
            onChangeMenu?.invoke(MenuType.add)
        }

        binding?.linearLayout3?.setOnClickListener {
            onChangeMenu?.invoke(MenuType.route)
            binding?.let {
                it.icon2.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorWhite))
                it.icon1.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGrey))
                it.icon.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGrey))
                it.icon3.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGrey))

                it.label2.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite))
                it.label1.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey))
                it.label.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey))
                it.label3.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey))
            }
        }

        binding?.linearLayout4?.setOnClickListener {
            onChangeMenu?.invoke(MenuType.notification)
            binding?.let {
                it.icon3.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorWhite))
                it.icon1.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGrey))
                it.icon.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGrey))
                it.icon2.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGrey))

                it.label3.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite))
                it.label1.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey))
                it.label.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey))
                it.label2.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey))
            }
        }
    }
}