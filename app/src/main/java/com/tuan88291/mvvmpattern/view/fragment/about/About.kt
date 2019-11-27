package com.tuan88291.mvvmpattern.view.fragment.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tuan88291.mvvmpattern.BaseFragment
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.databinding.AboutFragmentBinding
import com.tuan88291.mvvmpattern.utils.observe.AutoDisposable
import com.tuan88291.mvvmpattern.utils.observe.ObserveEasy

class About : BaseFragment() {
    private var binding: AboutFragmentBinding? = null
    private var autodis: AutoDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autodis = AutoDisposable(this.lifecycle)

    }
    override fun setView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.about_fragment, container, false)
        return binding!!.getRoot()
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        binding!!.title.setText(mContext()!!.getItem())

        object : ObserveEasy(){
            override fun getDispose(): AutoDisposable? {
                return autodis
            }

            override fun doBackground(): Any? {

                return null
            }

            override fun onFail(err: String) {
                super.onFail(err)
            }

            override fun onLoadComplete() {
                super.onLoadComplete()
            }

            override fun onLoading() {
                super.onLoading()
            }

            override fun onSuccess(result: Any?) {
                super.onSuccess(result)
            }

        }
    }
}
