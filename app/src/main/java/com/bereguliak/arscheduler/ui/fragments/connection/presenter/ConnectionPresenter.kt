package com.bereguliak.arscheduler.ui.fragments.connection.presenter

import com.bereguliak.arscheduler.core.presenter.BaseCoroutinePresenter
import com.bereguliak.arscheduler.data.local.user.UserLocalRepository
import com.bereguliak.arscheduler.model.CalendarInfo
import com.bereguliak.arscheduler.model.connection.CalendarLocation
import com.bereguliak.arscheduler.ui.fragments.connection.ConnectionContract
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException
import com.google.api.services.calendar.Calendar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConnectionPresenter @Inject constructor(private val view: ConnectionContract.View,
                                              private val credential: GoogleAccountCredential,
                                              private val client: Calendar,
                                              private val userLocalRepository: UserLocalRepository)
    : BaseCoroutinePresenter(), ConnectionContract.Presenter {

    //region ConnectionContract.Presenter
    override fun prepareChooseAccount() {
        view.chooseAccount(credential)
    }

    override fun loadUserInfo() {
        launch(exceptionHandler) {
            val userName = loadUserName()
            credential.selectedAccountName = userName

            if (userName.isNullOrEmpty()) {
                view.chooseAccount(credential)
            } else {
                view.setUserName(userName)
                view.accountConnected()
            }
        }
    }

    override fun saveUserName(userName: String) {
        credential.selectedAccountName = userName
        GlobalScope.launch {
            saveUserInfo(userName)
        }
    }

    override fun startDownloadDataFromCalendar() {
        launch {
            try {
                val result = loadInfoFromCalendar()
                view.userCalendarsLoaded()

                val locations = withDispatcherIO {
                    result.items.map { entry ->
                        CalendarLocation(entry.id, entry.summary)
                    }.toMutableList()
                }
                view.showUserCalendarLocations(locations)
            } catch (recoverableAuthIOException: UserRecoverableAuthIOException) {
                view.authorizationRequired(recoverableAuthIOException.intent)
            }
        }
    }

    override fun logout() {
        view.setUserName("")
        GlobalScope.launch {
            withDispatcherIO {
                userLocalRepository.clearUserInfo()
            }
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