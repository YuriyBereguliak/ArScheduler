package com.bereguliak.arscheduler.ui.fragments.loading

interface LoadingContract {
    interface View {
        fun showConnectionScreen()
        fun showSchedulerScreen()
    }

    interface Presenter {
        fun unSubscribe()

        fun startInitialization()
    }
}