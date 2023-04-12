package com.tuan88291.mvvmpattern.view.fragment.detailfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tuan88291.mvvmpattern.view.fragment.BaseChildFragment
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.data.local.model.DataProfile
import com.tuan88291.mvvmpattern.databinding.DetailFragmentBinding
import com.tuan88291.mvvmpattern.view.fragment.profilefragment.ProfileFragment
import org.koin.android.ext.android.inject

class DetailFragment : BaseChildFragment() {
    private var binding: DetailFragmentBinding? = null
    private val profile: DataProfile by inject()

    override fun setView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        return binding!!.getRoot()
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        profile.getName().observe(this, {
            binding?.text?.text = it
        })
        binding?.button2?.setOnClickListener {
            this.navigate(ProfileFragment())
        }
    }
}