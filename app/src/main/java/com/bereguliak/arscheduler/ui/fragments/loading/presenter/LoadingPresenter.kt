package com.bereguliak.arscheduler.ui.fragments.loading.presenter

import com.bereguliak.arscheduler.core.presenter.BaseCoroutinePresenter
import com.bereguliak.arscheduler.ui.fragments.loading.LoadingContract
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingPresenter(private val view: LoadingContract.View) : BaseCoroutinePresenter(), LoadingContract.Presenter {

    //region LoadingContract.Presenter
    override fun startInitialization() {
        launch {

            view.showNetworkConnectionCheck()
            delay(1000L)

            view.showDatabaseCheck()
            delay(1000L)

            view.showDatabaseCheck()
            delay(1000L)

            view.showConnectionScreen()
        }
    }
    //endregion
}