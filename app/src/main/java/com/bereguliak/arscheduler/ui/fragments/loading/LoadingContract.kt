package com.bereguliak.arscheduler.ui.fragments.loading

interface LoadingContract {
    interface View {
        fun showDatabaseCheck()
        fun showCalendarConnection()

        fun showConnectionScreen()
        fun showSchedulerScreen()
    }

    interface Presenter {
        fun startInitialization()
    }
}