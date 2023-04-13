package com.tuan88291.mvvmpattern.view.fragment.detailfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tuan88291.mvvmpattern.view.fragment.BaseChildFragment
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.databinding.DetailFragmentBinding
import com.tuan88291.mvvmpattern.view.fragment.profilefragment.ProfileFragment

class DetailFragment : BaseChildFragment() {
    private var binding: DetailFragmentBinding? = null

    override fun setView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        return binding!!.getRoot()
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.button2?.setOnClickListener {
            this.navigate(ProfileFragment())
        }
    }
}