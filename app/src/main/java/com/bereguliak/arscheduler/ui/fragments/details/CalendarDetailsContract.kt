package com.bereguliak.arscheduler.ui.fragments.details

import com.bereguliak.arscheduler.model.CalendarLocation

interface CalendarDetailsContract {
    interface View {

    }

    interface Presenter {
        fun loadEvents(info: CalendarLocation)
    }
}