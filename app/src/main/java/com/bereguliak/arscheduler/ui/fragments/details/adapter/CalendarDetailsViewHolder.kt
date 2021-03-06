package com.bereguliak.arscheduler.ui.fragments.details.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.core.adapter.MarginItemDecoration
import com.bereguliak.arscheduler.model.CalendarEvent
import com.bereguliak.arscheduler.model.EventAttendee
import java.text.SimpleDateFormat
import java.util.*

class CalendarDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.eventTitle)
    private val startTime = itemView.findViewById<TextView>(R.id.eventStartTime)
    private val endTime = itemView.findViewById<TextView>(R.id.eventEndTime)

    private val organizerName = itemView.findViewById<TextView>(R.id.organizerNameTextView)
    private val organizerIcon = itemView.findViewById<ImageView>(R.id.organizerIconImageView)

    private val description = itemView.findViewById<TextView>(R.id.descriptionTextView)
    private val descriptionIcon = itemView.findViewById<ImageView>(R.id.descriptionIconImageView)

    private val accountsIcon = itemView.findViewById<ImageView>(R.id.accountsIconImageView)
    private val accountsTitle = itemView.findViewById<TextView>(R.id.accountsTitleTextView)
    private val attendeesRecyclerView =  itemView.findViewById<RecyclerView>(R.id.accountsRecyclerView)

    private val attendeeAdapter by lazy { CalendarEventAttendeeAdapter() }
    private val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    init {
        attendeesRecyclerView.apply {
            val margin = itemView.context.resources.getDimension(R.dimen.margin_all_small).toInt()
            addItemDecoration(MarginItemDecoration(margin))

            adapter = attendeeAdapter
        }
    }

    //region CalendarDetailsViewHolder
    fun bind(event: CalendarEvent) {
        title.text = event.title
        handleEventDescription(event)
        handleEventOrganizer(event.organizer)
        handleEventAttendees(event.attendees)

        startTime.text = simpleDateFormat.format(event.startTime)
        endTime.text = simpleDateFormat.format(event.endTime)
    }

    //endregion

    //region Utility API
    private fun handleEventOrganizer(organizer: EventAttendee) {
        organizerName.text = organizer.getNameToDisplay().also {
            if (it.isEmpty()) changeVisibilityStatusOfOrganizerData(View.GONE)
            else changeVisibilityStatusOfOrganizerData(View.VISIBLE)
        }
    }

    private fun handleEventDescription(event: CalendarEvent) {
        if (event.description.isNullOrEmpty()) {
            changeVisibilityStatusOfDescription(View.GONE)
        } else {
            changeVisibilityStatusOfDescription(View.VISIBLE)
            description.text = event.description
        }
    }

    private fun handleEventAttendees(attendees: List<EventAttendee>) {
        if (attendees.isEmpty()) {
            changeVisibilityStatusOfAttendees(View.GONE)
        } else {
            changeVisibilityStatusOfAttendees(View.VISIBLE)
            if (attendeeAdapter.data.isEmpty()) attendeeAdapter.data = attendees.toMutableList()
        }
    }

    private fun changeVisibilityStatusOfOrganizerData(status: Int) {
        organizerName.visibility = status
        organizerIcon.visibility = status
    }

    private fun changeVisibilityStatusOfDescription(status: Int) {
        description.visibility = status
        descriptionIcon.visibility = status
    }

    private fun changeVisibilityStatusOfAttendees(status: Int) {
        accountsIcon.visibility = status
        accountsTitle.visibility = status
        attendeesRecyclerView.visibility = status
    }
    //endregion
}