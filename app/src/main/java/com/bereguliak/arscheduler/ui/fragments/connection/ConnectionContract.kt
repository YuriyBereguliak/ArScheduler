package com.bereguliak.arscheduler.ui.fragments.connection

import android.content.Intent
import com.bereguliak.arscheduler.model.connection.CalendarLocation
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential

interface ConnectionContract {
    interface View {
        fun showLoading()

        fun hideLoading()

        fun showNoNetworkError()

        fun chooseAccount()

        fun chooseAccountNotAllowed()

        fun setUserName(user: String)

        fun accountConnected()

        fun authorizationRequired(intent: Intent)

        fun userCalendarsLoaded()

        fun showUserCalendarLocations(data: MutableList<CalendarLocation>)
    }

    interface Presenter {
        fun loadUserInfo()

        fun userAccountSelected(userName: String)

        fun startDownloadDataFromCalendar()

        fun prepareChooseAccount()

        fun logout()

        fun unSubscribe()
    }
}