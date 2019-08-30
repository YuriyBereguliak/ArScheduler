package com.bereguliak.arscheduler.ui.fragments.details.presenter

import com.bereguliak.arscheduler.core.presenter.BaseCoroutinePresenter
import com.bereguliak.arscheduler.domain.calendar.location.CalendarOrchestrator
import com.bereguliak.arscheduler.model.CalendarLocation
import com.bereguliak.arscheduler.ui.fragments.details.CalendarDetailsContract
import com.bereguliak.arscheduler.utilities.L
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class CalendarDetailsPresenter(private val view: CalendarDetailsContract.View,
                               private val calendarOrchestrator: CalendarOrchestrator)
    : BaseCoroutinePresenter(), CalendarDetailsContract.Presenter {

    //region CalendarDetailsContract.Presenter
    override fun loadEvents(info: CalendarLocation) {
        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            view.hideLoading()
            exception.message?.let { L.e(it) }
        }
        launch(exceptionHandler) {
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
    //endregion

    //region Utility API
    private suspend fun loadEventsByCalendarId(info: CalendarLocation) = withDispatcherIO {
        calendarOrchestrator.loadEventsForCurrentDay(info)
    }
    //endregion
}