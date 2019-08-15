package com.bereguliak.arscheduler.domain.calendar.location

import com.google.api.services.calendar.model.CalendarList

interface CalendarLocationOrchestrator {
    fun initUserAccount()

    suspend fun loadLocations(): CalendarList?

    fun logout()
}