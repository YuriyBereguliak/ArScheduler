package com.bereguliak.arscheduler.ui.fragments.loading.presenter

import com.bereguliak.arscheduler.core.presenter.BaseCoroutinePresenter
import com.bereguliak.arscheduler.ui.fragments.loading.LoadingContract
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoadingPresenter @Inject constructor(private val view: LoadingContract.View) : BaseCoroutinePresenter(),
        LoadingContract.Presenter {

    //region LoadingContract.Presenter
    override fun startInitialization() {
        launch {
            delay(2000L)
            view.showConnectionScreen()
        }
    }
    //endregion
}