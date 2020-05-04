package com.bereguliak.arscheduler.di.module.ar

import com.bereguliak.arscheduler.di.scope.FragmentScope
import com.bereguliak.arscheduler.ui.fragments.ar.view.ArScheduleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ArScheduleModule {

    @ContributesAndroidInjector(modules = [ArScheduleContractModule::class])
    @FragmentScope
    abstract fun contributeArScheduleFragment(): ArScheduleFragment
}