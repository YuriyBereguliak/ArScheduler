package com.bereguliak.arscheduler.domain.calendar.location

import android.content.Context
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.domain.user.UserOrchestrator
import com.bereguliak.arscheduler.utilities.extensions.setEndOfTheDay
import com.bereguliak.arscheduler.utilities.extensions.setStartOfTheDay
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.util.DateTime
import com.google.api.client.util.ExponentialBackOff
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import com.google.api.services.calendar.model.CalendarList
import com.google.api.services.calendar.model.Events
import java.util.*
import javax.inject.Inject

class DefaultCalendarOrchestrator @Inject constructor(private val context: Context,
                                                      private val jsonFactory: JsonFactory,
                                                      private val httpTransport: HttpTransport,
                                                      private val userOrchestrator: UserOrchestrator)
    : CalendarOrchestrator {

    private var credential: GoogleAccountCredential? = null
    private var client: Calendar? = null

    //region CalendarOrchestrator
    override fun initUserAccount() {
        credential = GoogleAccountCredential
                .usingOAuth2(context, setOf(CalendarScopes.CALENDAR_READONLY))
                .setBackOff(ExponentialBackOff())
                .setSelectedAccountName(userOrchestrator.loadUserName())

        client = Calendar.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName(context.getString(R.string.app_name))
                .build()
    }

    override suspend fun loadLocations(): CalendarList? {
        return client?.calendarList()?.list()?.setFields(CALENDAR_FIELDS)?.execute()
    }

    override suspend fun loadEventsForCurrentDay(calendarId: String): Events? {
        val start = java.util.Calendar.getInstance().apply { setStartOfTheDay() }
        val end = java.util.Calendar.getInstance().apply { setEndOfTheDay() }
        return client?.events()?.list(calendarId)
                ?.setFields(EVENTS_FIELDS)
                ?.setTimeZone(DEFAULT_TIMEZONE)
                ?.setTimeMin(DateTime(start.time, TimeZone.getDefault()))
                ?.setTimeMax(DateTime(end.time, TimeZone.getDefault()))
                ?.setShowDeleted(false)
                ?.setOrderBy("startTime")
                ?.setSingleEvents(true)
                ?.execute()
    }

    override fun logout() {
        credential = null
        client = null
    }
    //endregion

    //region Utility structure
    companion object {
        private const val DEFAULT_TIMEZONE = "Europe/Kiev"

        private const val CALENDAR_FIELDS = "items(id, summary, backgroundColor)"
        private const val EVENTS_FIELDS = "items(attachments,attendees,colorId,description,end,endTimeUnspecified,id,originalStartTime,source,start,status,summary)"
    }
    //endregion
}