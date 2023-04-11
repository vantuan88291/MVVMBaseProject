package com.tuan88291.mvvmpattern.view.fragment.profilefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tuan88291.mvvmpattern.BaseChildFragment
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.databinding.ProfileFragmentBinding

class ProfileFragment : BaseChildFragment() {
    private var binding: ProfileFragmentBinding? = null

    override fun setView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        return binding!!.getRoot()
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {

    }
}