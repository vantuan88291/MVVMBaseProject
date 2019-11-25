package com.tuan88291.mvppatternkotlin

interface BaseContract : BaseView {
    fun onLoading()
    fun onLoadComplete()
    fun onError(mess: String)
}
