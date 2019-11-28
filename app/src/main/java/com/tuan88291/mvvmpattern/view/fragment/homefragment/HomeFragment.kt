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
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    private val homeViewModel: HomeViewModel by viewModel()
    private var binding: HomeFragmentBinding? = null
    private var page: Int = 1
    override fun setView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return binding!!.getRoot()
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel.getData().observe(this, Observer<DataUser> { this.processData(it) })
        homeViewModel.loading().observe(this, Observer<Boolean> { this.loading(it) })
        homeViewModel.error().observe(this, Observer<String> { this.error(it) })
        binding?.button?.setOnClickListener{
            homeViewModel.loadData(page)
            page++
        }
        binding?.btn?.setOnClickListener {
            homeViewModel.dispose()
        }
    }
    private fun processData(data: DataUser) {
        try {
            binding?.user = data.data?.get(0)
        }catch (e: Exception) {
            binding?.content?.text = "End of page"
        }
    }
    private fun loading(isLoading: Boolean) {
       if (isLoading) {
           binding?.title?.text = "loading"
       } else {
           binding?.title?.text = "loading success"
       }
    }
    private fun error(msg: String) {
        binding?.content?.text = msg
    }
}
