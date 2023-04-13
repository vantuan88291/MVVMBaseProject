package com.tuan88291.mvvmpattern.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tuan88291.mvvmpattern.view.activity.GuestActivity

abstract class BaseGuestFragment : Fragment() {
    private var context: GuestActivity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewCreated(view, savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as GuestActivity?
    }

    protected abstract fun setView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    protected abstract fun viewCreated(view: View, savedInstanceState: Bundle?)
    protected fun mContext(): GuestActivity? {
        return this.context
    }
    fun setLoading(isLoading: Boolean) {
        mContext()?.setLoading(isLoading)
    }
}