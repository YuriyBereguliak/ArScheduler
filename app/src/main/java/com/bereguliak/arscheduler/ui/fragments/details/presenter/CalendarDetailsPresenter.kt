package com.bereguliak.arscheduler.ui.fragments.details.presenter

import com.bereguliak.arscheduler.core.presenter.BaseCoroutinePresenter
import com.bereguliak.arscheduler.domain.calendar.location.CalendarOrchestrator
import com.bereguliak.arscheduler.model.CalendarLocation
import com.bereguliak.arscheduler.ui.fragments.details.CalendarDetailsContract
import com.bereguliak.arscheduler.utilities.L
import com.google.api.services.calendar.model.Events
import kotlinx.coroutines.launch
import javax.inject.Inject

class CalendarDetailsPresenter @Inject constructor(private val view: CalendarDetailsContract.View,
                                                   private val calendarOrchestrator: CalendarOrchestrator)
    : BaseCoroutinePresenter(), CalendarDetailsContract.Presenter {

    init {
        calendarOrchestrator.initUserAccount()
    }

    //region CalendarDetailsContract.Presenter
    override fun loadEvents(info: CalendarLocation) {
        launch {
            val events = loadEventsByCalendarId(info.id)
            L.d(events?.toPrettyString() ?: "")
        }
    }
    //endregion

    //region Utility API
    private suspend fun loadEventsByCalendarId(id: String): Events? = withDispatcherIO {
        calendarOrchestrator.loadEvents(id)
    }
    //endregion
}