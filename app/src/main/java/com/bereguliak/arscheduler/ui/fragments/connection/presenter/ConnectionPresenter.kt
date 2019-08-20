package com.bereguliak.arscheduler.ui.fragments.connection.presenter

import com.bereguliak.arscheduler.core.presenter.BaseCoroutinePresenter
import com.bereguliak.arscheduler.domain.calendar.location.CalendarLocationOrchestrator
import com.bereguliak.arscheduler.domain.user.UserOrchestrator
import com.bereguliak.arscheduler.model.connection.CalendarLocation
import com.bereguliak.arscheduler.ui.fragments.connection.ConnectionContract
import com.bereguliak.arscheduler.utilities.network.NetworkUtils
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException
import com.google.api.services.calendar.model.CalendarList
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConnectionPresenter @Inject constructor(private val view: ConnectionContract.View,
                                              private val calendarLocationOrchestrator: CalendarLocationOrchestrator,
                                              private val userOrchestrator: UserOrchestrator,
                                              private val networkUtils: NetworkUtils)
    : BaseCoroutinePresenter(), ConnectionContract.Presenter {

    //region ConnectionContract.Presenter
    override fun prepareChooseAccount() {
        launch {
            when (withDispatcherIO { userOrchestrator.isUserSignedIn() }) {
                true -> view.chooseAccountNotAllowed()
                false -> view.chooseAccount()
            }
        }
    }

    override fun loadUserInfo() {
        launch(exceptionHandler) {
            if (userOrchestrator.isUserSignedIn()) {
                loadUserName()?.let { userName ->
                    calendarLocationOrchestrator.initUserAccount()
                    view.setUserName(userName)
                    view.accountConnected()
                }
            } else {
                prepareChooseAccount()
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
            networkUtils.isConnectionAvailable().takeUnless { it }?.let {
                view.showNoNetworkError()
                return@launch
            }

            view.hideNoNetworkError()

            try {
                view.showLoading()

                val result = loadLocations()
                view.userCalendarsLoaded()

                val locations = mappingLocations(result)
                view.showUserCalendarLocations(locations)

            } catch (recoverableAuthIOException: UserRecoverableAuthIOException) {
                view.authorizationRequired(recoverableAuthIOException.intent)
            } finally {
                view.hideLoading()
            }
        }
    }

    override fun logout() {
        launch {
            withDispatcherIO {
                userOrchestrator.logout()
            }
            calendarLocationOrchestrator.logout()
            view.setUserName("")
        }
    }
    //endregion

    //region Utility API
    private suspend fun loadUserName() = withDispatcherIO {
        userOrchestrator.loadUserName()
    }

    private suspend fun saveUserInfo(userName: String) = withDispatcherIO {
        userOrchestrator.saveUserName(userName)
    }

    private suspend fun loadLocations() = withDispatcherIO {
        calendarLocationOrchestrator.loadLocations()
    }

    private suspend fun mappingLocations(result: CalendarList?) = withDispatcherIO {
        result?.items?.map { entry ->
            CalendarLocation(entry.id, entry.summary, entry.backgroundColor)
        }?.toMutableList() ?: mutableListOf()
    }
    //endregion
}