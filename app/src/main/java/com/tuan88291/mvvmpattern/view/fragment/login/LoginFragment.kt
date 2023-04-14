package com.tuan88291.mvvmpattern.view.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.data.local.model.login.DataLogin
import com.tuan88291.mvvmpattern.databinding.LoginFragmentBinding
import com.tuan88291.mvvmpattern.view.fragment.BaseGuestFragment
import com.tuan88291.mvvmpattern.view.fragment.State
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment: BaseGuestFragment() {
    private var binding: LoginFragmentBinding? = null
    private val loginViewModel: LoginViewModel by viewModel()

    override fun setView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        return binding!!.getRoot()
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.loginViewModel = loginViewModel
        loginViewModel.getData().observe(this, {onLoginProcess(it)})
    }
    private fun onLoginProcess(state: State) {
        when (state) {
            is State.Failure -> Toast.makeText(mContext(), state.message, Toast.LENGTH_LONG).show()
            is State.Loading -> setLoading(state.loading)
            is State.Success<*> -> {
                if (state.data is DataLogin) {
                    mContext()?.navigateTomain()
                }
            }
        }
    }
}