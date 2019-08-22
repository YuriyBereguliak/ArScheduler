package com.bereguliak.arscheduler.ui.fragments.details.presenter

import com.bereguliak.arscheduler.core.presenter.BaseCoroutinePresenter
import com.bereguliak.arscheduler.domain.calendar.location.CalendarOrchestrator
import com.bereguliak.arscheduler.model.CalendarLocation
import com.bereguliak.arscheduler.ui.fragments.details.CalendarDetailsContract
import com.bereguliak.arscheduler.utilities.L
import com.google.api.services.calendar.model.Events
import kotlinx.coroutines.CoroutineExceptionHandler
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
        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            exception.message?.let { L.e(it) }
        }
        launch(exceptionHandler) {
            val events = loadEventsByCalendarId(info.id)
            if (events == null) {
                view.showNoEventsResult()
            } else {
                val filter = filterConfirmedEvents(events)
                view.showEvents(filter)
            }
        }
    }
    //endregion

    //region Utility API
    private suspend fun loadEventsByCalendarId(id: String): Events? = withDispatcherIO {
        calendarOrchestrator.loadEventsForCurrentDay(id)
    }

    private suspend fun filterConfirmedEvents(events: Events) = withDispatcherIO {
        events.items.filter { it.status == EventStatusType.CONFIRMED.type }
    }
    //endregion
}

enum class EventStatusType(val type: String) {
    CONFIRMED("confirmed"),
    CANCELED("cancelled")
}