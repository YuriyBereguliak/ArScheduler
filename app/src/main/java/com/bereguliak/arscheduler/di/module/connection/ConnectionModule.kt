package com.bereguliak.arscheduler.di.module.connection

import com.bereguliak.arscheduler.di.module.google.GoogleApiModule
import com.bereguliak.arscheduler.ui.fragments.connection.view.ConnectionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ConnectionIncludeModule::class, GoogleApiModule::class])
abstract class ConnectionModule {
    @ContributesAndroidInjector(modules = [ConnectionContractModule::class])
    @ConnectionScope
    internal abstract fun contributeConnectionFragment(): ConnectionFragment
}