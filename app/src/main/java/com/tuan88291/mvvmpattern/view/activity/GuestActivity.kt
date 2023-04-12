package com.tuan88291.mvvmpattern.view.activity

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.tuan88291.mvvmpattern.BaseActivity
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.databinding.ActivityGuestBinding
import com.tuan88291.mvvmpattern.view.fragment.login.LoginFragment


class GuestActivity : BaseActivity() {

    private lateinit var binding: ActivityGuestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_guest)
        addFragment(LoginFragment(), "login")
    }
}

