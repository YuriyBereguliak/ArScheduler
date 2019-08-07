package com.bereguliak.arscheduler.ui.fragments.connection

import android.content.Context
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential

interface ConnectionContract {
    interface View {
        fun chooseAccount(credentials: GoogleAccountCredential)
        fun accountConnected()
    }

    interface Presenter {
        fun loadUserInfo()

        fun saveUserName(userName:String)

        fun startDownloadDataFromCalendar()
    }
}