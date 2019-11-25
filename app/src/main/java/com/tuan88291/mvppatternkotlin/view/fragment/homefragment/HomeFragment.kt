package com.tuan88291.mvppatternkotlin.view.fragment.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tuan88291.mvppatternkotlin.BaseFragment
import com.tuan88291.mvppatternkotlin.R
import com.tuan88291.mvppatternkotlin.data.local.model.DataUser
import com.tuan88291.mvppatternkotlin.databinding.HomeFragmentBinding

class HomeFragment : BaseFragment(), HomeContract {
    private var homeViewModel: HomeViewModel? = null
    private var binding: HomeFragmentBinding? = null

    override fun setView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return binding!!.getRoot()
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel = createViewModel()
        homeViewModel?.getData()?.observe(this, Observer<DataUser> { this.processData(it) })
        binding?.button?.setOnClickListener{
            homeViewModel?.loadData()
        }
    }
    private fun processData(data: DataUser) {
        binding?.user = data.data?.get(0)
    }
    private fun createViewModel(): HomeViewModel {
        return ViewModelProviders.of(this, HomeFactory(this)).get(HomeViewModel::class.java)
    }
    override fun onLoading() {
        binding?.title?.text = "loading"
    }

    override fun onLoadComplete() {
        binding?.title?.text = "loading success"

    }

    override fun onError(mess: String) {

    }
}
