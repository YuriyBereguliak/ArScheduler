package com.bereguliak.arscheduler.domain.calendar.location

import android.content.Context
import com.bereguliak.arscheduler.domain.user.UserOrchestrator
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.util.ExponentialBackOff
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import com.google.api.services.calendar.model.CalendarList
import com.google.api.services.calendar.model.Events
import javax.inject.Inject

class DefaultCalendarLocationOrchestrator @Inject constructor(private val context: Context,
                                                              private val jsonFactory: JsonFactory,
                                                              private val httpTransport: HttpTransport,
                                                              private val userOrchestrator: UserOrchestrator)
    : CalendarLocationOrchestrator {

    private var credential: GoogleAccountCredential? = null
    private var client: Calendar? = null

    //region CalendarLocationOrchestrator
    override fun initUserAccount() {
        credential = GoogleAccountCredential
                .usingOAuth2(context, setOf(CalendarScopes.CALENDAR_READONLY))
                .setBackOff(ExponentialBackOff())
                .setSelectedAccountName(userOrchestrator.loadUserName())

        client = Calendar.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("BereguliakArScheduler")
                .build()
    }

    override suspend fun loadLocations(): CalendarList? {
        return client?.calendarList()?.list()?.setFields(CALENDAR_FIELDS)?.execute()
    }

    override suspend fun loadEvents(calendarId: String): Events? {
        return client?.events()?.list(calendarId)
                ?.setFields(EVENTS_FIELDS)
                ?.execute()
    }

    override fun logout() {
        credential = null
        client = null
    }
    //endregion

    //region Utility structure
    companion object {
        private const val CALENDAR_FIELDS = "items(id, summary, backgroundColor)"
        private const val EVENTS_FIELDS = "items(attachments,attendees,colorId,description,end,endTimeUnspecified,id,originalStartTime,source,start,status,summary)"
    }
    //endregion
}