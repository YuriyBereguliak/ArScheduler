package com.bereguliak.arscheduler.di.module.connection

import com.bereguliak.arscheduler.di.scope.FragmentScope
import com.bereguliak.arscheduler.ui.fragments.connection.ConnectionContract
import com.bereguliak.arscheduler.ui.fragments.connection.presenter.ConnectionPresenter
import com.bereguliak.arscheduler.ui.fragments.connection.view.ConnectionFragment
import dagger.Binds
import dagger.Module

@Module
interface ConnectionContractModule {
    @Binds
    @FragmentScope
    fun bindsConnectionView(view: ConnectionFragment): ConnectionContract.View

    @Binds
    @FragmentScope
    fun bindsConnectionPresenter(presenter: ConnectionPresenter): ConnectionContract.Presenter
}