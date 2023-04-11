package com.tuan88291.mvvmpattern.utils

import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson

object Utils {
    fun log(msg: String, data: Any) {
        try {
            LogUtils.e("$msg: --->" + Gson().toJson(data))
        } catch (e: Exception) {
            LogUtils.e("$msg: --->" + e.message)
        }
    }
    fun backFragment(activity: AppCompatActivity) {
        try {
            val fm = activity.supportFragmentManager
            if (fm.backStackEntryCount > 0) {
                fm.popBackStack()
            }
        } catch (e: Exception) {

        }
    }
}