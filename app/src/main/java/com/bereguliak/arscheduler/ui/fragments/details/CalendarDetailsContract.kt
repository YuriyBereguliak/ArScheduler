package com.bereguliak.arscheduler.ui.fragments.details

import com.bereguliak.arscheduler.model.CalendarLocation
import com.google.api.services.calendar.model.Event

interface CalendarDetailsContract {
    interface View {
        fun showEvents(events: List<Event>)

        fun showNoEventsResult()
    }

    interface Presenter {
        fun loadEvents(info: CalendarLocation)
    }
}