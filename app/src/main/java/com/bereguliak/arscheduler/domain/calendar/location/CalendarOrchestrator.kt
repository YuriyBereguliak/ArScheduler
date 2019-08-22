package com.bereguliak.arscheduler.domain.calendar.location

import com.google.api.services.calendar.model.CalendarList
import com.google.api.services.calendar.model.Events

interface CalendarOrchestrator {
    fun initUserAccount()

    suspend fun loadLocations(): CalendarList?

    suspend fun loadEvents(calendarId: String): Events?

    fun logout()
}