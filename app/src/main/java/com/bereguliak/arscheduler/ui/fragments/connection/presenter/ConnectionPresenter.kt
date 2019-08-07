package com.bereguliak.arscheduler.ui.fragments.connection.presenter

import com.bereguliak.arscheduler.core.presenter.BaseCoroutinePresenter
import com.bereguliak.arscheduler.data.local.user.UserLocalRepository
import com.bereguliak.arscheduler.model.CalendarInfo
import com.bereguliak.arscheduler.ui.fragments.connection.ConnectionContract
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.services.calendar.Calendar
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConnectionPresenter @Inject constructor(private val view: ConnectionContract.View,
                                              private val credential: GoogleAccountCredential,
                                              private val client: Calendar,
                                              private val userLocalRepository: UserLocalRepository)
    : BaseCoroutinePresenter(), ConnectionContract.Presenter {

    //region ConnectionContract.Presenter
    override fun loadUserInfo() {
        launch {
            val userName = loadUserName()
            credential.selectedAccountName = userName

            if (userName.isNullOrEmpty()) {
                view.chooseAccount(credential)
            } else {
                view.accountConnected()
                startDownloadDataFromCalendar()
            }
        }
    }

    override fun saveUserName(userName: String) {
        launch {
            credential.selectedAccountName = userName
            saveUserInfo(userName)
        }
    }

    override fun startDownloadDataFromCalendar() {
        launch(exceptionHandler) {
            val result = loadInfoFromCalendar()
            println(result.toString())
        }
    }
    //endregion

    //region Utility API
    private suspend fun loadUserName() = withDispatcherIO {
        userLocalRepository.loadUserName()
    }

    private suspend fun saveUserInfo(userName: String) = withDispatcherIO {
        userLocalRepository.saveUserName(userName)
    }

    private suspend fun loadInfoFromCalendar() = withDispatcherIO {
        client.calendarList().list().setFields(CalendarInfo.FEED_FIELDS).execute()
    }
    //endregion
}