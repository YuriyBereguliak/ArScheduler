package com.bereguliak.arscheduler.ui.activity.main

import android.support.annotation.UiThread

@UiThread
interface MainNavigator {
    fun showLoadingScreen()
    fun showConnectionScreen()
    fun showCalendarDetailsScreen(calendar: String)
    fun showArSchedulerScreen()
    fun showSettingsScreen()
}