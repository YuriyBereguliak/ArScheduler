package com.bereguliak.arscheduler.ui.fragments.details.presenter

import com.bereguliak.arscheduler.core.presenter.BaseCoroutinePresenter
import com.bereguliak.arscheduler.domain.calendar.location.CalendarOrchestrator
import com.bereguliak.arscheduler.model.CalendarLocation
import com.bereguliak.arscheduler.ui.fragments.details.CalendarDetailsContract
import com.bereguliak.arscheduler.utilities.L
import com.google.api.services.calendar.model.CalendarListEntry
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class CalendarDetailsPresenter(private val view: CalendarDetailsContract.View,
                               private val calendarOrchestrator: CalendarOrchestrator)
    : BaseCoroutinePresenter(), CalendarDetailsContract.Presenter {

    private val calendarExceptionHandler = CoroutineExceptionHandler { _, exception ->
        view.hideLoading()
        exception.message?.let { L.e(it) }
    }

    //region CalendarDetailsContract.Presenter
    override fun loadEvents(info: CalendarLocation) {
        launch(calendarExceptionHandler) {
            view.showLoading()
            val events = loadEventsByCalendarId(info)
            if (events.isEmpty()) {
                view.showNoEventsResult()
            } else {
                view.showEvents(events)
            }
            view.hideLoading()
        }
    }

    override fun loadEventsByCalendarName(name: String) {
        launch(calendarExceptionHandler) {
            view.showLoading()

            val calendar = findCalendarEntry(name)
            if (calendar == null) {
                view.showNoEventsResult()
                return@launch
            }

            val info = mapCalendarEntry(calendar)
            loadEvents(info)
        }
    }
    //endregion

    //region Utility API
    private suspend fun loadEventsByCalendarId(info: CalendarLocation) = withDispatcherIO {
        calendarOrchestrator.loadEventsForCurrentDay(info)
    }

    private suspend fun findCalendarEntry(name: String): CalendarListEntry? {
        return withDispatcherIO {
            var calendarEntry: CalendarListEntry? = null
            calendarOrchestrator.loadLocations()?.let { list ->
                calendarEntry = list.items.firstOrNull { it.summary.contains(name) }
            }
            calendarEntry
        }
    }

    private suspend fun mapCalendarEntry(calendarListEntry: CalendarListEntry) = withDispatcherIO {
        CalendarLocation(calendarListEntry.id, calendarListEntry.summary, calendarListEntry.backgroundColor)
    }
    //endregion
}