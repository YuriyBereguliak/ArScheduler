package com.bereguliak.arscheduler.model

data class CalendarEvent(val id: String,
                         val title: String,
                         val description: String?,
                         val startTime: Long,
                         val endTime: Long,
                         val attendees: List<EventAttendee>)

data class EventAttendee(val email: String,
                         val displayName: String?,
                         val response: String?)