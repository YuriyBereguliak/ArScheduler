package com.bereguliak.arscheduler.ui.fragments.details

import com.bereguliak.arscheduler.model.CalendarEvent
import com.bereguliak.arscheduler.model.CalendarLocation

interface CalendarDetailsContract {
    interface View {
        fun showEvents(events: List<CalendarEvent>)

        fun showNoEventsResult()

        fun showLoading()

        fun hideLoading()
    }

    interface Presenter {
        fun loadEvents(info: CalendarLocation)

        fun unSubscribe()
    }
}