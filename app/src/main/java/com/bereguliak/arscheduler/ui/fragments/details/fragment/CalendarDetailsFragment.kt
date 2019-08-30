package com.bereguliak.arscheduler.ui.fragments.details.fragment

import android.os.Bundle
import android.support.annotation.LayoutRes
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.core.ui.BaseFragment
import com.bereguliak.arscheduler.domain.calendar.location.CalendarOrchestrator
import com.bereguliak.arscheduler.model.CalendarLocation
import com.bereguliak.arscheduler.ui.fragments.details.view.CalendarEventsView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_calendar_details.*
import javax.inject.Inject

class CalendarDetailsFragment : BaseFragment() {

    @Inject
    internal lateinit var calendarOrchestrator: CalendarOrchestrator

    private var eventsView: CalendarEventsView? = null

    //region BaseFragment
    @LayoutRes
    override fun getContentViewId() = R.layout.fragment_calendar_details

    override fun initView() {
        AndroidSupportInjection.inject(this)
        initEventView()
        initBackButtonClickListener()
        initDataFromArgs()
    }

    override fun onStop() {
        super.onStop()
        eventsView?.release()
    }
    //endregion

    //region Utility API
    private fun initEventView() {
        eventsView = CalendarEventsView(context!!).apply {
            addOrchestrator(calendarOrchestrator)
        }
        calendarEventsViewContainer.addView(eventsView)
    }

    private fun initDataFromArgs() {
        arguments?.let { args ->
            args.getParcelable<CalendarLocation>(ARG_CALENDAR_INFO)?.let { info ->
                calendarSummaryTextView.text = info.summary
                calendarSummaryTextView.setBackgroundColor(info.color())
                eventsView?.calendarInfo = info
                eventsView?.loadData()
            }
        }
    }

    private fun initBackButtonClickListener() {
        calendarSummaryBackButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }
    //endregion

    //region Utility structure
    companion object {

        private const val ARG_CALENDAR_INFO =
                "com.bereguliak.arscheduler.ui.fragments.details.view.CALENDAR_INFO"

        fun newInstance(calendar: CalendarLocation): CalendarDetailsFragment {
            return CalendarDetailsFragment().apply {
                arguments = Bundle(1).apply {
                    putParcelable(ARG_CALENDAR_INFO, calendar)
                }
            }
        }
    }
    //endregion
}