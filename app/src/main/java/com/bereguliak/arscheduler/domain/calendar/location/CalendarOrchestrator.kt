package com.bereguliak.arscheduler.domain.calendar.location

import com.bereguliak.arscheduler.model.CalendarEvent
import com.google.api.services.calendar.model.CalendarList

interface CalendarOrchestrator {
    fun initUserAccount()

    suspend fun loadLocations(): CalendarList?

    suspend fun loadEventsForCurrentDay(calendarId: String): List<CalendarEvent>

    fun logout()
}