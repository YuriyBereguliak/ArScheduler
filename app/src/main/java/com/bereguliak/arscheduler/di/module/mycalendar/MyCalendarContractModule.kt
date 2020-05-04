package com.bereguliak.arscheduler.di.module.mycalendar

import com.bereguliak.arscheduler.di.scope.FragmentScope
import com.bereguliak.arscheduler.ui.fragments.mycalendar.MyCalendarContract
import com.bereguliak.arscheduler.ui.fragments.mycalendar.presenter.MyCalendarPresenter
import com.bereguliak.arscheduler.ui.fragments.mycalendar.view.MyCalendarArFragment
import dagger.Binds
import dagger.Module

@Module
interface MyCalendarContractModule {
    @Binds
    @FragmentScope
    fun bindsMyCalendarContractView(fragment: MyCalendarArFragment): MyCalendarContract.View

    @Binds
    @FragmentScope
    fun bindsMyCalendarContractPresenter(presenter: MyCalendarPresenter): MyCalendarContract.Presenter
}