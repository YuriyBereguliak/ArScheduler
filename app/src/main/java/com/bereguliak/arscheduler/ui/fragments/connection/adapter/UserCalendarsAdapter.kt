package com.bereguliak.arscheduler.ui.fragments.connection.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.model.connection.CalendarLocation

class UserCalendarsAdapter : RecyclerView.Adapter<UserCalendarsViewHolder>() {

    var data = mutableListOf<CalendarLocation>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    //region RecyclerView
    override fun onCreateViewHolder(group: ViewGroup, position: Int): UserCalendarsViewHolder {
        val view = LayoutInflater.from(group.context).inflate(R.layout.item_calendar_locations, group, false)
        return UserCalendarsViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(viewHolder: UserCalendarsViewHolder, position: Int) {
        viewHolder.bind(data[position])
    }
    //endregion
}