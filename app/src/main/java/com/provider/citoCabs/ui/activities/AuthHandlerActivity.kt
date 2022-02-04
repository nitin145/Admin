package com.provider.citoCabs.ui.activities

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.provider.citoCabs.R
import com.provider.citoCabs.base.Prefs
import com.provider.citoCabs.base.ScopedActivity
import com.provider.citoCabs.base.ScopedFragment
import com.provider.citoCabs.databinding.AuthHandlerActivityBinding

class AuthHandlerActivity : ScopedActivity() {

    lateinit var mBinding: AuthHandlerActivityBinding
    lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.apply {
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
        mBinding = DataBindingUtil.setContentView(
            this,
            R.layout.auth_handler_activity
        )
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController




    }




}