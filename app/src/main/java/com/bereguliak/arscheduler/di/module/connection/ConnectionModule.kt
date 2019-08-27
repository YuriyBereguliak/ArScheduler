package com.bereguliak.arscheduler.di.module.connection

import com.bereguliak.arscheduler.di.scope.FragmentScope
import com.bereguliak.arscheduler.ui.fragments.connection.view.ConnectionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ConnectionModule {
    @ContributesAndroidInjector(modules = [ConnectionContractModule::class])
    @FragmentScope
    internal abstract fun contributeConnectionFragment(): ConnectionFragment
}