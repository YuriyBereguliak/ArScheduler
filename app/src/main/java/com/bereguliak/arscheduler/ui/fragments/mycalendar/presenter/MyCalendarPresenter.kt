package com.bereguliak.arscheduler.ui.fragments.mycalendar.presenter

import com.bereguliak.arscheduler.core.presenter.BaseCoroutinePresenter
import com.bereguliak.arscheduler.ui.fragments.mycalendar.MyCalendarContract
import javax.inject.Inject

class MyCalendarPresenter @Inject constructor(private val view: MyCalendarContract.View)
    : BaseCoroutinePresenter(), MyCalendarContract.Presenter {
}