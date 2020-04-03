package com.tuan88291.mvvmpattern.utils

import android.text.Editable
import android.text.TextWatcher

abstract class TextWatcherWrap: TextWatcher {
    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        onTextChangedWrap(p0, p1, p2, p3)
    }
    protected open fun onTextChangedWrap(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
}