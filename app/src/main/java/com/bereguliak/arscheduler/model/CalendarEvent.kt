package com.bereguliak.arscheduler.model

data class CalendarEvent(val id: String,
                         val title: String,
                         val description: String?,
                         val startTime: Long,
                         val endTime: Long,
                         val organizer: EventAttendee,
                         var attendees: List<EventAttendee>)

data class EventAttendee(val email: String,
                         val displayName: String?,
                         val response: String?,
                         val isOrganizer: Boolean = false) {
    fun getNameToDisplay() = when {
        !displayName.isNullOrEmpty() -> displayName
        email.isNotEmpty() -> email
        else -> ""
    }

    fun getStatus() = when (response) {
        AttendeeStatus.ACCEPTED.value -> AttendeeStatus.ACCEPTED
        AttendeeStatus.DECLINED.value -> AttendeeStatus.DECLINED
        AttendeeStatus.TENTATIVE.value -> AttendeeStatus.TENTATIVE
        else -> AttendeeStatus.NEED_ACTION
    }
}

enum class AttendeeStatus(val value: String) {
    ACCEPTED("accepted"),
    DECLINED("declined"),
    NEED_ACTION("needsAction"),
    TENTATIVE("tentative")
}