package com.tuan88291.mvvmpattern.data.local.model.login

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.databinding.library.baseAdapters.BR

class ParamLogin : Observable {
    @Transient
    private var mCallbacks: PropertyChangeRegistry? = null

    @get:Bindable
    var email: String? = null

    @get:Bindable
    var password: String? = null

    fun onChangeUserName(text: CharSequence, start: Int, before: Int, count: Int) {
        this.email = text.toString()
        notifyPropertyChanged(BR.email)
    }

    fun onChangePass(text: CharSequence, start: Int, before: Int, count: Int) {
        this.password = text.toString()
        notifyPropertyChanged(BR.password)
    }
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        synchronized(this) {
            if (mCallbacks == null) {
                mCallbacks = PropertyChangeRegistry()
            }
        }
        mCallbacks!!.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks!!.remove(callback)
    }
    fun notifyPropertyChanged(fieldId: Int) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks!!.notifyCallbacks(this, fieldId, null)
    }
}
