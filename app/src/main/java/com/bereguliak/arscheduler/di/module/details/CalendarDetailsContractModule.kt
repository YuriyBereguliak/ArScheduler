package com.bereguliak.arscheduler.di.module.details

import com.bereguliak.arscheduler.di.scope.FragmentScope
import com.bereguliak.arscheduler.ui.fragments.details.CalendarDetailsContract
import com.bereguliak.arscheduler.ui.fragments.details.presenter.CalendarDetailsPresenter
import com.bereguliak.arscheduler.ui.fragments.details.view.CalendarDetailsFragment
import dagger.Binds
import dagger.Module

@Module
interface CalendarDetailsContractModule {
    @Binds
    @FragmentScope
    fun bindsCalendarDetailsContractView(view: CalendarDetailsFragment): CalendarDetailsContract.View

    @Binds
    @FragmentScope
    fun bindsCalendarDetailsContractPresenter(presenter: CalendarDetailsPresenter): CalendarDetailsContract.Presenter
}