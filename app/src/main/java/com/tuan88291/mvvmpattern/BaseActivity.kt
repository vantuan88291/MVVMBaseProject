package com.tuan88291.mvvmpattern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tuan88291.mvvmpattern.utils.Utils

open class BaseActivity : AppCompatActivity(), BaseView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
    }

    protected fun addFragment(fragment: Fragment?, tag: String) {
        if (fragment != null) {
            try {
                val fragmentTag = supportFragmentManager.findFragmentByTag(tag)

                if (fragmentTag == null) {
                     supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.contentHome, fragment, tag)
                        .commit()
                }
            } catch (e: Exception) {

            }
        }
    }
    protected fun popBackStack() {
        Utils.backFragment(this)
    }
    protected fun navigateFragment(fragment: Fragment?, tag: String) {
        if (fragment != null) {
            try {
                val fragmentTag = supportFragmentManager.findFragmentByTag(tag)

                if (fragmentTag == null) {
                    supportFragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right)
                        .add(R.id.contentHome, fragment, tag)
                        .addToBackStack(fragment.javaClass.canonicalName)
                        .commitAllowingStateLoss()
                }
            } catch (e: Exception) {

            }
        }
    }

    override fun setErrorParent(data: Any) {

    }
}
