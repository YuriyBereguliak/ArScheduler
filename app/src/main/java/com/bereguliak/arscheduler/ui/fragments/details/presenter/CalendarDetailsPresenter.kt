package com.bereguliak.arscheduler.ui.fragments.details.presenter

import com.bereguliak.arscheduler.core.presenter.BaseCoroutinePresenter
import com.bereguliak.arscheduler.domain.calendar.location.CalendarLocationOrchestrator
import com.bereguliak.arscheduler.model.CalendarLocation
import com.bereguliak.arscheduler.ui.fragments.details.CalendarDetailsContract
import kotlinx.coroutines.launch
import javax.inject.Inject

class CalendarDetailsPresenter @Inject constructor(private val view: CalendarDetailsContract.View,
                                                   private val calendarLocationOrchestrator: CalendarLocationOrchestrator)
    : BaseCoroutinePresenter(), CalendarDetailsContract.Presenter {

    //region CalendarDetailsContract.Presenter
    override fun loadEvents(info: CalendarLocation) {
        launch {

        }
    }
    //endregion
}