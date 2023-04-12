package com.tuan88291.mvvmpattern.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.tuan88291.mvvmpattern.view.activity.MainActivity

abstract class BaseChildFragment : Fragment(), BaseView {
    private var context: MainActivity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setErrorParent(data: Any) {
        AlertDialog.Builder(requireContext())
            .setTitle("Your Alert")
            .setMessage(data.toString())
            .setCancelable(false)
            .setPositiveButton("ok") { dialog, which ->

            }.show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext()?.onStateBottomMenu(true)
        viewCreated(view, savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as MainActivity?
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mContext()?.supportFragmentManager?.fragments?.size!! <= 1) {
            mContext()?.onStateBottomMenu(false)
        }
    }
    fun navigate(fragment: Fragment) {
        mContext()?.onNavigateToFragment(fragment)
    }
    fun setLoading(isLoading: Boolean) {
        mContext()?.setLoading(isLoading)
    }

    protected abstract fun setView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    protected abstract fun viewCreated(view: View, savedInstanceState: Bundle?)
    protected fun mContext(): MainActivity? {
        return this.context
    }

}