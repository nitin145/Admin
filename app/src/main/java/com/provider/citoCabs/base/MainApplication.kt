package com.provider.citoCabs.base

import android.app.Activity
import android.app.Application
import com.provider.citoCabs.dataSources.remote.RemoteDataSource
import com.provider.citoCabs.dataSources.remote.RemoteDataSourceImp
import com.provider.citoCabs.network.core.APIService
import com.provider.citoCabs.repo.RemoteRepository
import com.provider.citoCabs.repo.RemoteRepositoryImpl
import com.provider.citoCabs.ui.fragments.auth.viewModel.LoginViewModelFactory
import com.provider.citoCabs.ui.fragments.addRides.viewModel.RidesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class MainApplication : Application(), KodeinAware {

    companion object {
        lateinit var instance: MainApplication
        fun get(): MainApplication = instance


    }


    private var mCurrentActivity: Activity? = null



    fun getCurrentActivity(): Activity? {
        return mCurrentActivity
    }

    fun setCurrentActivity(mCurrentActivity: Activity?) {
        this.mCurrentActivity = mCurrentActivity
    }


        override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MainApplication))

        bind() from singleton { APIService() }

        bind<RemoteRepository>() with singleton {
            RemoteRepositoryImpl(instance())
        }




        bind<RemoteDataSource>() with singleton {
            RemoteDataSourceImp(instance())
        }


        //Factory
        bind() from singleton { LoginViewModelFactory(instance()) }
        bind() from singleton { RidesViewModelFactory(instance()) }


    }

    override fun onCreate() {
        super.onCreate()
        instance = this


    }


}