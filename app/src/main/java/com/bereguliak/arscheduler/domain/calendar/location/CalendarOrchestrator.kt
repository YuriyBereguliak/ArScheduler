package com.bereguliak.arscheduler.domain.calendar.location

import com.bereguliak.arscheduler.model.CalendarEvent
import com.bereguliak.arscheduler.model.CalendarLocation
import com.google.api.services.calendar.model.CalendarList

interface CalendarOrchestrator {
    fun initUserAccount()

    suspend fun loadLocations(): CalendarList?

    suspend fun loadEventsForCurrentDay(info: CalendarLocation): List<CalendarEvent>

    fun logout()
}