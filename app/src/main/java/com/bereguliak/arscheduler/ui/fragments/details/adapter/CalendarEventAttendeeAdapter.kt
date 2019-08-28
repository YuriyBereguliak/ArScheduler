package com.bereguliak.arscheduler.ui.fragments.details.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.model.AttendeeStatus
import com.bereguliak.arscheduler.model.EventAttendee

class CalendarEventAttendeeAdapter : RecyclerView.Adapter<CalendarEventAttendeeViewHolder>() {

    var data = mutableListOf<EventAttendee>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    //region RecyclerView.Adapter
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CalendarEventAttendeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event_attendee, parent, false)
        return CalendarEventAttendeeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CalendarEventAttendeeViewHolder, position: Int) {
        holder.bind(data[position])
    }
    //endregion
}

class CalendarEventAttendeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val attendeeName = itemView.findViewById<TextView>(R.id.attendeeNameTextView)
    private val attendeeResponseStatus = itemView.findViewById<ImageView>(R.id.attendeeResponseStatusImageView)

    //region CalendarEventAttendeeViewHolder
    fun bind(attendee: EventAttendee) {
        attendeeName.text = attendee.getNameToDisplay()

        attendeeResponseStatus.setImageResource(when (attendee.getStatus()) {
            AttendeeStatus.ACCEPTED -> R.drawable.ic_done
            AttendeeStatus.DECLINED -> R.drawable.ic_reject
            AttendeeStatus.TENTATIVE -> R.drawable.ic_maybe
            AttendeeStatus.NEED_ACTION -> R.drawable.ic_wait
        })
    }
    //endregion
}