package com.tuan88291.mvppatternkotlin.view.fragment.homefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeFactory(val callback: HomeContract): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(callback) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}