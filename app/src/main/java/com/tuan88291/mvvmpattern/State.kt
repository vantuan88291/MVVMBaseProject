package com.tuan88291.mvvmpattern

sealed class State {
    class Failure(val message:String): State()
    object Loading: State()
    class Success<T>(val data: T): State()
}