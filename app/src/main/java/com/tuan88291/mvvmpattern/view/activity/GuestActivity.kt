package com.tuan88291.mvvmpattern.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.tuan88291.mvvmpattern.BaseActivity
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.databinding.ActivityGuestBinding
import com.tuan88291.mvvmpattern.utils.Common
import com.tuan88291.mvvmpattern.utils.SharedPrefs
import com.tuan88291.mvvmpattern.view.fragment.login.LoginFragment


class GuestActivity : BaseActivity() {

    private lateinit var binding: ActivityGuestBinding

    private var keep = true
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition{keep}
        val token = SharedPrefs.instance?.get(Common.TOKEN, String::class.java)
        if (token == null || token.equals("")) {
            keep = false
        } else {
            navigateTomain()
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_guest)
        addFragment(LoginFragment(), "login")
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

