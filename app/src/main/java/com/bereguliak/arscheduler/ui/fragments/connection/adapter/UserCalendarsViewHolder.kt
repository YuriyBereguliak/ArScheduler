package com.bereguliak.arscheduler.ui.fragments.connection.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.model.CalendarLocation

class UserCalendarsViewHolder(itemView: View,
                              private val clickListener: OnUserCalendarClickListener?)
    : RecyclerView.ViewHolder(itemView) {

    private val itemSummaryTextView = itemView.findViewById<TextView>(R.id.itemLocationName)
    private val container = itemView.findViewById<ConstraintLayout>(R.id.itemCalendar)

    //region UserCalendarsViewHolder
    fun bind(data: CalendarLocation) {
        itemSummaryTextView.text = data.summary
        container.setOnClickListener {
            clickListener?.onCalendarClickListener(data)
        }
    }
    //endregion
}