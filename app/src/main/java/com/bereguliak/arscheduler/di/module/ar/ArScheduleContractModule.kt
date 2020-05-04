package com.bereguliak.arscheduler.di.module.ar

import com.bereguliak.arscheduler.di.scope.FragmentScope
import com.bereguliak.arscheduler.ui.fragments.ar.ArScheduleContract
import com.bereguliak.arscheduler.ui.fragments.ar.presenter.ArSchedulePresenter
import com.bereguliak.arscheduler.ui.fragments.ar.view.ArScheduleFragment
import dagger.Binds
import dagger.Module

@Module
interface ArScheduleContractModule {
    @Binds
    @FragmentScope
    fun bindsArScheduleContractView(view: ArScheduleFragment): ArScheduleContract.View

    @Binds
    @FragmentScope
    fun bindsArScheduleContractPresenter(presenter: ArSchedulePresenter): ArScheduleContract.Presenter
}