package com.tuan88291.mvvmpattern.view.fragment.homefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.tuan88291.mvvmpattern.BaseFragment
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.data.local.entity.DataRoom
import com.tuan88291.mvvmpattern.data.local.model.DataUser
import com.tuan88291.mvvmpattern.data.local.room.livedata.DBmodel
import com.tuan88291.mvvmpattern.databinding.HomeFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    private val homeViewModel: HomeViewModel by viewModel()
    private var binding: HomeFragmentBinding? = null
    private val db: DBmodel by viewModel()

    override fun setView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return binding!!.getRoot()
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel.getData().observe(this, Observer { this.processData(it) })
        homeViewModel.getStateLoadAdapter().observe(this, Observer { binding?.list?.setStateLoading(it) })
        db.getAll().observe(this, Observer { this.onDataChange(it) })
        homeViewModel.loadData(true)
        binding?.apply {
            btn.setOnClickListener {
                db.insertData(DataRoom("tuan", (0..10).random()))
            }
            list.onLoadmore = {
                homeViewModel.loadData(false)
            }
        }
    }
    private fun onDataChange(data: MutableList<DataRoom>) {
        binding?.apply {
            list.visibility = View.GONE
            listDb.visibility = View.VISIBLE
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
            list.visibility = View.VISIBLE
            listDb.visibility = View.GONE
        }
        try {
            binding?.list?.setData(data.data!!)
            if(data.data?.size == 0) {
                homeViewModel.finishLoadMore()
            }
        }catch (e: Exception) {
        }
    }
    private fun loading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
    private fun error(msg: Any) {
        toast(msg.toString())
    }
    fun toast(msg: String) {
        Toast.makeText(mContext(), msg, Toast.LENGTH_LONG).show()
    }
}
