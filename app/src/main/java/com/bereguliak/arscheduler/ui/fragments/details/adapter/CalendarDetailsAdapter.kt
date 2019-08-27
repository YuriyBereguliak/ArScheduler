package com.bereguliak.arscheduler.ui.fragments.details.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.model.CalendarEvent
import com.google.api.services.calendar.model.Event

class CalendarDetailsAdapter : RecyclerView.Adapter<CalendarDetailsViewHolder>() {

    var data = mutableListOf<CalendarEvent>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    //region RecyclerView.Adapter
    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): CalendarDetailsViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_calendar_event, parent, false)
        return CalendarDetailsViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CalendarDetailsViewHolder, position: Int) {
        viewHolder.bind(data[position])
    }
    //endregion
}