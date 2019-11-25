package com.tuan88291.mvvmpattern.view.fragment.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tuan88291.mvvmpattern.BaseFragment
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.data.local.model.DataUser
import com.tuan88291.mvvmpattern.databinding.HomeFragmentBinding

class HomeFragment : BaseFragment() {
    private var homeViewModel: HomeViewModel? = null
    private var binding: HomeFragmentBinding? = null

    override fun setView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return binding!!.getRoot()
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel = createViewModel()
        homeViewModel?.getData()?.observe(this, Observer<DataUser> { this.processData(it) })
        homeViewModel?.loading()?.observe(this, Observer<Boolean> { this.loading(it) })
        binding?.button?.setOnClickListener{
            homeViewModel?.loadData()
        }
    }
    private fun processData(data: DataUser) {
        val rnds = (0..5).random()
        binding?.user = data.data?.get(rnds)
    }
    private fun createViewModel(): HomeViewModel {
        return ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }
    private fun loading(isLoading: Boolean) {
       if (isLoading) {
           binding?.title?.text = "loading"
       } else {
           binding?.title?.text = "loading success"
       }
    }
}
