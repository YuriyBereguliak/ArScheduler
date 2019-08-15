package com.bereguliak.arscheduler.di.module.connection

import com.bereguliak.arscheduler.ui.fragments.connection.ConnectionContract
import com.bereguliak.arscheduler.ui.fragments.connection.presenter.ConnectionPresenter
import com.bereguliak.arscheduler.ui.fragments.connection.view.ConnectionFragment
import dagger.Binds
import dagger.Module

@Module
interface ConnectionContractModule {
    @Binds
    @ConnectionScope
    fun bindsConnectionView(view: ConnectionFragment): ConnectionContract.View

    @Binds
    @ConnectionScope
    fun bindsConnectionPresenter(presenter: ConnectionPresenter): ConnectionContract.Presenter
}