package com.bereguliak.arscheduler.ui.fragments.connection.presenter

import com.bereguliak.arscheduler.core.presenter.BaseCoroutinePresenter
import com.bereguliak.arscheduler.data.local.user.UserLocalRepository
import com.bereguliak.arscheduler.domain.calendar.location.CalendarLocationOrchestrator
import com.bereguliak.arscheduler.model.connection.CalendarLocation
import com.bereguliak.arscheduler.ui.fragments.connection.ConnectionContract
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException
import com.google.api.services.calendar.model.CalendarList
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConnectionPresenter @Inject constructor(private val view: ConnectionContract.View,
                                              private val calendarLocationOrchestrator: CalendarLocationOrchestrator,
                                              private val userLocalRepository: UserLocalRepository)
    : BaseCoroutinePresenter(), ConnectionContract.Presenter {

    //region ConnectionContract.Presenter
    override fun prepareChooseAccount() {
        view.chooseAccount()
    }

    override fun loadUserInfo() {
        launch(exceptionHandler) {
            val userName = loadUserName()

            if (userName.isNullOrEmpty()) {
                prepareChooseAccount()
            } else {
                calendarLocationOrchestrator.initUserAccount()
                view.setUserName(userName)
                view.accountConnected()
            }
        }
    }

    override fun userAccountSelected(userName: String) {
        launch {
            saveUserInfo(userName)
            calendarLocationOrchestrator.initUserAccount()
            startDownloadDataFromCalendar()
        }
    }

    override fun startDownloadDataFromCalendar() {
        launch {
            try {
                val result = loadLocations()
                view.userCalendarsLoaded()

                val locations = mappingLocations(result)
                view.showUserCalendarLocations(locations)

            } catch (recoverableAuthIOException: UserRecoverableAuthIOException) {
                view.authorizationRequired(recoverableAuthIOException.intent)
            }
        }
    }

    override fun logout() {
        launch {
            withDispatcherIO {
                userLocalRepository.clearUserInfo()
            }
            calendarLocationOrchestrator.logout()
            view.setUserName("")
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

    private suspend fun loadLocations() = withDispatcherIO {
        calendarLocationOrchestrator.loadLocations()
    }

    private suspend fun mappingLocations(result: CalendarList?) = withDispatcherIO {
        result?.items?.map { entry ->
            CalendarLocation(entry.id, entry.summary)
        }?.toMutableList() ?: mutableListOf()
    }
    //endregion
}