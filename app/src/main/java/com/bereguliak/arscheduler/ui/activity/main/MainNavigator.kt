package com.bereguliak.arscheduler.ui.activity.main

import android.support.annotation.UiThread
import com.bereguliak.arscheduler.model.CalendarLocation

@UiThread
interface MainNavigator {
    fun showLoadingScreen()
    fun showConnectionScreen()
    fun showCalendarDetailsScreen(calendar: CalendarLocation)
    fun showArSchedulerScreen()
    fun showMyCalendarArScheduler(calendar: CalendarLocation)
    fun showSettingsScreen()
}