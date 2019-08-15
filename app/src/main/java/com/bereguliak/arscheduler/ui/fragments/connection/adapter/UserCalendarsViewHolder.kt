package com.bereguliak.arscheduler.ui.fragments.connection.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.model.connection.CalendarLocation

class UserCalendarsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val itemSummaryTextView = itemView.findViewById<TextView>(R.id.itemLocationName)

    //region UserCalendarsViewHolder
    fun bind(data: CalendarLocation) {
        itemSummaryTextView.text = data.summary
    }
    //endregion
}