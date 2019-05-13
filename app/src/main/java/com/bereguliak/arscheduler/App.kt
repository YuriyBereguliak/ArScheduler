package com.bereguliak.arscheduler

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

class App: Application() {
    //region Application
    override fun onCreate() {
        super.onCreate()
        initCrashlytics()
    }
    //endregion

    //region Utility API
    private fun initCrashlytics() {
        Fabric.with(this, Crashlytics())
    }
    //endregion
}