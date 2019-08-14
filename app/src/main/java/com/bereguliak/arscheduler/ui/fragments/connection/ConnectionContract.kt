package com.bereguliak.arscheduler.ui.fragments.connection

import android.content.Intent
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential

interface ConnectionContract {
    interface View {
        fun chooseAccount(credentials: GoogleAccountCredential)

        fun accountConnected()

        fun authorizationRequired(intent: Intent)
    }

    interface Presenter {
        fun loadUserInfo()

        fun saveUserName(userName: String)

        fun startDownloadDataFromCalendar()

        fun prepareChooseAccount()
    }
}