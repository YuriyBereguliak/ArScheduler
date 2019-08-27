package com.bereguliak.arscheduler.ui.fragments.connection.adapter

import com.bereguliak.arscheduler.model.CalendarLocation

interface OnUserCalendarClickListener {
    fun onCalendarClickListener(calendar: CalendarLocation)
}