package com.tuan88291.mvvmpattern.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.tuan88291.mvvmpattern.BaseActivity
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.databinding.ActivityGuestBinding
import com.tuan88291.mvvmpattern.utils.Common
import com.tuan88291.mvvmpattern.utils.SharedPrefs
import com.tuan88291.mvvmpattern.view.fragment.State
import com.tuan88291.mvvmpattern.view.fragment.login.LoginFragment
import com.tuan88291.mvvmpattern.view.fragment.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class GuestActivity : BaseActivity() {

    private lateinit var binding: ActivityGuestBinding
    private val loginViewModel: LoginViewModel by viewModel()
    private var keep = true
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition{keep}
        val token = SharedPrefs.instance?.get(Common.TOKEN, String::class.java)
        if (token == null || token.equals("")) {
            keep = false
        } else {
            loginViewModel
                .getProfile()
                .getData()
                .observe(this, {onProfileProcess(it)})
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_guest)
        addFragment(LoginFragment(), "login")
    }

    private fun onProfileProcess(state: State) {
        when (state) {
            is State.Failure -> {
                Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                keep = false
            }
            is State.Loading -> {}
            is State.Success<*> -> {
                navigateTomain()
            }
        }
    }

    fun setLoading(loading: Boolean) {
        binding.loading.visibility = if (loading) View.VISIBLE else View.GONE
    }

    fun navigateTomain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
        finish()
    }
}

