package com.tuan88291.mvvmpattern.view.fragment


sealed class State {
    data class Success<T>(val data: T): State()
    data class Failure(val message:String): State()
    data class Loading(val loading: Boolean): State()
}