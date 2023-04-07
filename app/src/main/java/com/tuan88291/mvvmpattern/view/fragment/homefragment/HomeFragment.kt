package com.tuan88291.mvvmpattern.view.fragment.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.tuan88291.mvvmpattern.BaseFragment
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.State
import com.tuan88291.mvvmpattern.data.local.entity.DataRoom
import com.tuan88291.mvvmpattern.data.local.model.DataUser
import com.tuan88291.mvvmpattern.databinding.HomeFragmentBinding
import com.tuan88291.mvvmpattern.utils.SharedPrefs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    private val homeViewModel: HomeViewModel by viewModel()
    private var binding: HomeFragmentBinding? = null
    override fun setView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return binding!!.getRoot()
    }

    var disposable: Disposable? = null
    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel.getStateLoadAdapter()
            .observe(this, Observer { binding?.list?.setStateLoading(it) })
        homeViewModel.getAllDatabase().observe(this, Observer { this.onDataChange(it) })
        homeViewModel
            .loadData(true)
            .getData()
            .observe(this, Observer { this.processData(it) })
        binding?.apply {
            btn.setOnClickListener {
                homeViewModel.insertDatabase(DataRoom("tuan", (0..10).random()))
            }
            list.onLoadmore = {
                homeViewModel.loadData(false)
            }
            button.setOnClickListener {
                SharedPrefs.instance?.put("num", (0..10).random())
            }
        }
        disposable = SharedPrefs.instance?.get("num", Int::class.java)
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribe {
            binding?.button?.text = it.toString()
        }
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    private fun onDataChange(data: MutableList<DataRoom>) {
        binding?.apply {
            list.isGone = true
            listDb.isGone = false
            listDb.setData(data)
        }
    }

    private fun processData(state: State) {
        when (state) {
            is State.Failure -> error(state.message)
            is State.Loading -> loading(state.loading)
            is State.Success<*> -> setDataList(state.data as DataUser)
        }
    }

    private fun setDataList(data: DataUser) {
        binding?.apply {
            list.isGone = false
            listDb.isGone = true
        }
        try {
            binding?.list?.setData(data.data!!)
        } catch (e: Exception) {
        }
    }

    private fun loading(isLoading: Boolean) {
        mContext()?.setLoading(isLoading)
    }

    private fun error(msg: Any) {
        toast(msg.toString())
    }

    fun toast(msg: String) {
        Toast.makeText(mContext(), msg, Toast.LENGTH_LONG).show()
    }
}
