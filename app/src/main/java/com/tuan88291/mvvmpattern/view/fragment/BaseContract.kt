package com.tuan88291.mvvmpattern.view.fragment

interface BaseContract : BaseView {
    fun onLoading()
    fun onLoadComplete()
    fun onError(mess: String)
}
