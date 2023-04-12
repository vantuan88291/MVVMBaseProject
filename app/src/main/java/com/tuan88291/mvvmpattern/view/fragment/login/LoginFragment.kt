package com.tuan88291.mvvmpattern.view.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.databinding.LoginFragmentBinding
import com.tuan88291.mvvmpattern.view.fragment.BaseGuestFragment

class LoginFragment: BaseGuestFragment() {
    private var binding: LoginFragmentBinding? = null

    override fun setView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        return binding!!.getRoot()
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {

    }
}