package com.bereguliak.arscheduler.ui.fragments.connection.presenter

import android.content.Context
import com.bereguliak.arscheduler.data.local.user.DefaultUserLocalRepository
import com.bereguliak.arscheduler.data.local.user.UserLocalRepository
import com.bereguliak.arscheduler.ui.fragments.connection.ConnectionContract
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import javax.inject.Inject

class ConnectionPresenter @Inject constructor(private val view: ConnectionContract.View) :
    ConnectionContract.Presenter {

    private val transport = AndroidHttp.newCompatibleTransport()
    private val jsonFactory: JsonFactory = GsonFactory.getDefaultInstance()

    private lateinit var userLocalRepository: UserLocalRepository

    private var credential: GoogleAccountCredential? = null
    private var client: Calendar? = null

    //region ConnectionContract.Presenter
    override fun init(context: Context) {
        userLocalRepository = DefaultUserLocalRepository(context)

        credential = GoogleAccountCredential.usingOAuth2(context, setOf(CalendarScopes.CALENDAR)).apply {
            selectedAccountName = userLocalRepository.loadUserName()
        }

        client = Calendar.Builder(transport, jsonFactory, credential)
            .setApplicationName("BereguliakArScheduler")
            .build()
    }
    //endregion
}