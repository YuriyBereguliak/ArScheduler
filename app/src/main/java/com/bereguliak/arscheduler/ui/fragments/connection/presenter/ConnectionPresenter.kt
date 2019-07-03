package com.bereguliak.arscheduler.ui.fragments.connection.presenter

import android.content.Context
import com.bereguliak.arscheduler.core.presenter.BaseCoroutinePresenter
import com.bereguliak.arscheduler.data.local.user.UserLocalRepository
import com.bereguliak.arscheduler.ui.fragments.connection.ConnectionContract
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ConnectionPresenter @Inject constructor(
    context: Context,
    private val view: ConnectionContract.View,
    private val jsonFactory: JsonFactory,
    private val transport: HttpTransport,
    private val userLocalRepository: UserLocalRepository
) : BaseCoroutinePresenter(), ConnectionContract.Presenter {

    private val credential = GoogleAccountCredential.usingOAuth2(context, setOf(CalendarScopes.CALENDAR))

    private val client = Calendar.Builder(transport, jsonFactory, credential)
        .setApplicationName("BereguliakArScheduler")
        .build()

    //region ConnectionContract.Presenter
    override fun init(context: Context) {
        launch {
            val userName = withContext(Dispatchers.IO) { userLocalRepository.loadUserName() }
            credential.selectedAccountName = userName
        }
    }
    //endregion
}