package com.bereguliak.arscheduler.ui.fragments.details.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.model.CalendarEvent
import java.text.SimpleDateFormat
import java.util.*

class CalendarDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.eventTitle)
    private val startTime = itemView.findViewById<TextView>(R.id.eventStartTime)
    private val endTime = itemView.findViewById<TextView>(R.id.eventEndTime)

    //region CalendarDetailsViewHolder
    fun bind(event: CalendarEvent) {
        title.text = event.title

        val simpleDateFormat = SimpleDateFormat("HH:MM", Locale.getDefault())
        startTime.text = simpleDateFormat.format(event.startTime)
        endTime.text = simpleDateFormat.format(event.endTime)
    }
    //endregion
}