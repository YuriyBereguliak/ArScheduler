package com.bereguliak.arscheduler.di.module.mycalendar

import com.bereguliak.arscheduler.di.scope.FragmentScope
import com.bereguliak.arscheduler.ui.fragments.mycalendar.view.MyCalendarArFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MyCalendarModule {
    @ContributesAndroidInjector(modules = [MyCalendarContractModule::class])
    @FragmentScope
    internal abstract fun contributeMyCalendarArFragment(): MyCalendarArFragment
}