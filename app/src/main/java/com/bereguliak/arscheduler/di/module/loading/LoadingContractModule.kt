package com.bereguliak.arscheduler.di.module.loading

import com.bereguliak.arscheduler.ui.fragments.loading.LoadingContract
import com.bereguliak.arscheduler.ui.fragments.loading.presenter.LoadingPresenter
import com.bereguliak.arscheduler.ui.fragments.loading.view.LoadingFragment
import dagger.Binds
import dagger.Module

@Module
interface LoadingContractModule {
    @Binds
    @LoadingScope
    fun bindsLoadingView(view: LoadingFragment): LoadingContract.View

    @Binds
    @LoadingScope
    fun bindsLoadingPresenter(presenter: LoadingPresenter): LoadingContract.Presenter
}