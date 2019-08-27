package com.bereguliak.arscheduler.ui.fragments.details.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.utilities.getDateTime
import com.google.api.services.calendar.model.Event
import java.text.SimpleDateFormat
import java.util.*

class CalendarDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.eventTitle)
    private val startTime = itemView.findViewById<TextView>(R.id.eventStartTime)
    private val endTime = itemView.findViewById<TextView>(R.id.eventEndTime)

    //region CalendarDetailsViewHolder
    fun bind(event: Event) {
        title.text = event.summary

        val simpleDateFormat = SimpleDateFormat("HH:MM", Locale.getDefault())

        getDateTime(event.start.dateTime)?.let {
            startTime.text = simpleDateFormat.format(it)
        }
        getDateTime(event.end.dateTime)?.let {
            endTime.text = simpleDateFormat.format(it)
        }
    }
    //endregion
}