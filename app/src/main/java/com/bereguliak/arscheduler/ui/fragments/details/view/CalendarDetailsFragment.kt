package com.bereguliak.arscheduler.ui.fragments.details.view

import android.os.Bundle
import android.support.annotation.LayoutRes
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.core.ui.BaseFragment
import com.bereguliak.arscheduler.model.CalendarLocation
import com.bereguliak.arscheduler.ui.fragments.details.CalendarDetailsContract
import com.bereguliak.arscheduler.utilities.L
import com.google.api.services.calendar.model.Event
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_calendar_details.*
import javax.inject.Inject

class CalendarDetailsFragment : BaseFragment(), CalendarDetailsContract.View {

    @Inject
    lateinit var presenter: CalendarDetailsContract.Presenter

    //region BaseFragment
    @LayoutRes
    override fun getContentViewId() = R.layout.fragment_calendar_details

    override fun initView() {
        AndroidSupportInjection.inject(this)
        initBackButtonClickListener()
        initRoom()
    }
    //endregion

    //region Utility API
    private fun initRoom() {
        arguments?.let { args ->
            args.getParcelable<CalendarLocation>(ARG_CALENDAR_INFO)?.let { info ->
                calendarSummaryTextView.text = info.summary
                calendarSummaryTextView.setBackgroundColor(info.color())
                presenter.loadEvents(info)
            }
        }
    }

    private fun initBackButtonClickListener() {
        calendarSummaryBackButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }
    //endregion

    //region CalendarDetailsContract.View
    override fun showEvents(events: List<Event>) {
        L.d(events.toString())
    }

    override fun showNoEventsResult() {

    }
    //endregion

    //region Utility structure
    companion object {

        private const val ARG_CALENDAR_INFO = "com.bereguliak.arscheduler.ui.fragments.details.view.CALENDAR_INFO"

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