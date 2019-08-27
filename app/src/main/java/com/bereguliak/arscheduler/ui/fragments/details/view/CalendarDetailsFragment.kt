package com.bereguliak.arscheduler.ui.fragments.details.view

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.View
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.core.ui.BaseFragment
import com.bereguliak.arscheduler.model.CalendarEvent
import com.bereguliak.arscheduler.model.CalendarLocation
import com.bereguliak.arscheduler.ui.fragments.details.CalendarDetailsContract
import com.bereguliak.arscheduler.ui.fragments.details.adapter.CalendarDetailsAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_calendar_details.*
import javax.inject.Inject

class CalendarDetailsFragment : BaseFragment(), CalendarDetailsContract.View {

    @Inject
    lateinit var presenter: CalendarDetailsContract.Presenter

    private val adapter by lazy { CalendarDetailsAdapter() }

    //region BaseFragment
    @LayoutRes
    override fun getContentViewId() = R.layout.fragment_calendar_details

    override fun initView() {
        AndroidSupportInjection.inject(this)
        initAdapterForRecyclerView()
        initBackButtonClickListener()
        initDataFromArgs()
    }
    //endregion

    //region Utility API
    private fun initDataFromArgs() {
        arguments?.let { args ->
            args.getParcelable<CalendarLocation>(ARG_CALENDAR_INFO)?.let { info ->
                calendarSummaryTextView.text = info.summary
                calendarSummaryTextView.setBackgroundColor(info.color())
                presenter.loadEvents(info)
            }
        }
    }

    private fun initAdapterForRecyclerView() {
        calendarDetailsEventRecyclerView.adapter = adapter
    }

    private fun initBackButtonClickListener() {
        calendarSummaryBackButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }
    //endregion

    //region CalendarDetailsContract.View
    override fun showEvents(events: List<CalendarEvent>) {
        calendarDetailsEmptyViewContainer.visibility = View.GONE
        adapter.data = events.toMutableList()
    }

    override fun showNoEventsResult() {
        calendarDetailsEmptyViewContainer.visibility = View.VISIBLE
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