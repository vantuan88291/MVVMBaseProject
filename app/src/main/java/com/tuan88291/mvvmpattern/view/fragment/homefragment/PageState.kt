package com.tuan88291.mvvmpattern.view.fragment.homefragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PageState: ViewModel() {
    var currentPosition: MutableLiveData<Int> = MutableLiveData()
}