package com.bereguliak.arscheduler.di.module.connection

import com.bereguliak.arscheduler.ui.fragments.connection.view.ConnectionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ConnectionModule {
    @ContributesAndroidInjector(modules = [ConnectionContractModule::class])
    @ConnectionScope
    internal abstract fun contributeLoginCredentialsFragment(): ConnectionFragment
}