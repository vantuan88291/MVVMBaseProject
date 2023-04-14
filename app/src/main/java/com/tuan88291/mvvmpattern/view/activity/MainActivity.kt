package com.tuan88291.mvvmpattern.view.activity

import android.app.ActionBar.LayoutParams
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.tuan88291.mvvmpattern.BaseActivity
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.data.local.model.GlobalData
import com.tuan88291.mvvmpattern.databinding.ActivityMainBinding
import com.tuan88291.mvvmpattern.databinding.NavHeaderMainBinding
import com.tuan88291.mvvmpattern.utils.Common
import com.tuan88291.mvvmpattern.utils.SharedPrefs
import com.tuan88291.mvvmpattern.utils.observe.AutoDisposable
import com.tuan88291.mvvmpattern.utils.observe.addTo
import com.tuan88291.mvvmpattern.view.components.MenuType
import com.tuan88291.mvvmpattern.view.fragment.chat.ChatFragment
import com.tuan88291.mvvmpattern.view.fragment.homefragment.HomeFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    var binding: ActivityMainBinding? = null
    private var autodis: AutoDisposable? =  null
    private var paramsContent: RelativeLayout.LayoutParams? = null
    private var marginBottomMenu: Int? = null
    private val profile: GlobalData by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autodis = AutoDisposable(this.lifecycle)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val headerNav = NavHeaderMainBinding.bind(binding?.navView?.getHeaderView(0)!!)
        setSupportActionBar(binding?.appBar?.toolbar)
        marginBottomMenu = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            59F,
            this.resources.getDisplayMetrics()
        ).toInt()
        paramsContent = RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        val toggle = ActionBarDrawerToggle(
            this, binding?.drawerLayout, binding?.appBar?.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding?.let {
            it.drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            it.navView.setNavigationItemSelectedListener(this)
            it.appBar.title.text = "Call API"
        }
        binding?.appBar?.contentMain?.bottomNavigation?.onChangeMenu = {
            when(it) {
                MenuType.home -> {
                    addFragment(HomeFragment(), "Home")
                }
                MenuType.map -> {
                    addFragment(ChatFragment(), "Chat")
                }
                MenuType.add -> {

                }
                MenuType.route -> {

                }
                MenuType.notification -> {

                }
            }
        }
        addFragment(HomeFragment(), "Home")
        profile.getUser().observe(this, {headerNav.dataProfile = it})

    }

    fun onStateBottomMenu(hide: Boolean) {
        binding?.appBar?.contentMain?.bottomNavigation?.visibility = if (hide) View.GONE else View.VISIBLE
        binding?.appBar?.toolbar?.visibility = if (hide) View.GONE else View.VISIBLE
        if (hide) {
            paramsContent?.setMargins(0,0,0,0)
        } else {
            paramsContent?.setMargins(0,0,0, marginBottomMenu!!)
        }
        binding?.appBar?.contentMain?.contentHome?.layoutParams = paramsContent
        if (hide) {
            binding?.drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        } else {
            binding?.drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }
    fun setLoading(loading: Boolean) {
        binding?.appBar?.contentMain?.loading?.visibility = if (loading) View.VISIBLE else View.GONE
    }

    fun onNavigateToFragment(fragment: Fragment) {
        this.navigateFragment(fragment, fragment.javaClass.canonicalName!!)
    }

    override fun onBackPressed() {
        if (binding?.drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            binding?.drawerLayout?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun setTyping(msg: String) {
        binding?.appBar?.apply {
            typing.isGone = false
            typing.text = "$msg is typing..."
        }
        setUpTyping()
    }

    fun setUpTyping() {
        Observable.just(true).delay(3000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                binding?.appBar?.typing?.isGone = true
            }
            .subscribe().addTo(autodis!!)

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                binding?.appBar?.title?.text = "Call API"
                addFragment(HomeFragment(), "Home")
            }
            R.id.nav_gallery -> {
                binding?.appBar?.title?.text = "Chat socket"
                addFragment(ChatFragment(), "Chat")

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {
                SharedPrefs.instance?.removeByKey(Common.TOKEN)
                val intent = Intent(this, GuestActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
                startActivity(intent)
                finish()
            }
        }

        binding?.drawerLayout?.closeDrawer(GravityCompat.START)
        return true
    }
}
