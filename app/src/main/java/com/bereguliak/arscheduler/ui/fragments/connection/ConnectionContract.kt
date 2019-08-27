package com.bereguliak.arscheduler.ui.fragments.connection

import android.content.Intent
import com.bereguliak.arscheduler.model.CalendarLocation

interface ConnectionContract {
    interface View {
        fun showLoading()

        fun hideLoading()

        fun showNoNetworkError()

        fun hideNoNetworkError()

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