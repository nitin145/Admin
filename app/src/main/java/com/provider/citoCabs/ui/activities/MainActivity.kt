package com.provider.citoCabs.ui.activities

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.provider.citoCabs.R
import com.provider.citoCabs.base.MainApplication
import com.provider.citoCabs.base.ScopedActivity
import com.provider.citoCabs.databinding.ActivityMainBinding
import com.provider.citoCabs.ui.fragments.addRides.viewModel.RidesViewModel
import com.provider.citoCabs.ui.fragments.addRides.viewModel.RidesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class MainActivity : ScopedActivity(), KodeinAware {
    lateinit var mBinding: ActivityMainBinding
    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: RidesViewModelFactory by instance()
    lateinit var mViewModel: RidesViewModel
    lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    companion object {
        var currentDestination: Int = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.apply {
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            statusBarColor = Color.TRANSPARENT
        }
        setStatusBarColor(R.color.gray_view_color)
        mViewModel =
            ViewModelProvider(this, viewModelFactory).get(RidesViewModel::class.java)
        mBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        mBinding.clickHandler = this.ClickHandler()
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_dash_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupObserver()
        setupObserver()


    }



    override fun onResume() {
        super.onResume()
        initMyApp()


    }

    private fun initMyApp() {
        myApp = applicationContext as MainApplication
        myApp.setCurrentActivity(this)

    }

    inner class ClickHandler {

        fun addRide() {

        }

    }


    private fun setupObserver() {

        mViewModel.apply {
            showLoading.observe(this@MainActivity, Observer {
                if (it == true) {
                    showProgress()
                } else hideProgress()
            })

            showMessage.observe(this@MainActivity, Observer {
                hideProgress()
                if (!it.isNullOrEmpty()) {
                    showToast(it, "")
                }
                showMessage.postValue(null)

            })

        }

    }







}


