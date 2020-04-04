package com.tuan88291.mvvmpattern.view.adapter

sealed class AdapterStateLoad {
    object Loading : AdapterStateLoad()
    object LoadingSuccess : AdapterStateLoad()
    object FinishLoadMore : AdapterStateLoad()
}