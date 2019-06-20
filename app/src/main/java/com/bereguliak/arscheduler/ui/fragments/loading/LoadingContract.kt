package com.bereguliak.arscheduler.ui.fragments.loading

interface LoadingContract {
    interface View {
        fun showNetworkConnectionCheck()
        fun showDatabaseCheck()
        fun showCalendarConnection()

        fun showConnectionScreen()
        fun showSchedulerScreen()
    }

    interface Presenter {
        fun onSubscribe()
        fun unSubscribe()

        fun startInitialization()
    }
}