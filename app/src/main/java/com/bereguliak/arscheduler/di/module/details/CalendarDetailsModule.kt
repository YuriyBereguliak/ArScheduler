package com.bereguliak.arscheduler.di.module.details

import com.bereguliak.arscheduler.di.scope.FragmentScope
import com.bereguliak.arscheduler.ui.fragments.details.fragment.CalendarDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CalendarDetailsModule {
    @ContributesAndroidInjector
    @FragmentScope
    internal abstract fun contributeCalendarDetailsFragment(): CalendarDetailsFragment
}