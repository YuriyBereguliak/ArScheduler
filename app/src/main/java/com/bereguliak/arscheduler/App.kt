package com.bereguliak.arscheduler

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.bereguliak.arscheduler.di.component.AppComponent
import com.bereguliak.arscheduler.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class App : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    internal lateinit var dispatchingActivityAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    internal lateinit var dispatchingFragmentAndroidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var appComponent: AppComponent

    //region Application
    override fun onCreate() {
        super.onCreate()
        initDI()
    }
    //endregion

    //region HasActivityInjector
    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityAndroidInjector
    //endregion

    //region HasSupportFragmentInjector
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingFragmentAndroidInjector
    //endregion

    //region Utility API
    private fun initDI() {
        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
    }
    //endregion
}