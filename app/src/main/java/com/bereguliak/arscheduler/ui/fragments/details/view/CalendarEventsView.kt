package com.bereguliak.arscheduler.ui.fragments.details.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.core.adapter.MarginItemDecoration
import com.bereguliak.arscheduler.domain.calendar.location.CalendarOrchestrator
import com.bereguliak.arscheduler.model.CalendarEvent
import com.bereguliak.arscheduler.model.CalendarLocation
import com.bereguliak.arscheduler.ui.fragments.details.CalendarDetailsContract
import com.bereguliak.arscheduler.ui.fragments.details.adapter.CalendarDetailsAdapter
import com.bereguliak.arscheduler.ui.fragments.details.presenter.CalendarDetailsPresenter
import kotlinx.android.synthetic.main.view_events.view.*

class CalendarEventsView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr), CalendarDetailsContract.View {

    private var presenter: CalendarDetailsContract.Presenter? = null

    private val adapter by lazy { CalendarDetailsAdapter() }

    var calendarInfo: CalendarLocation? = null

    init {
        inflate(context, R.layout.view_events, this)
        initAdapterForRecyclerView()
    }

    //region CalendarEventsView
    fun addOrchestrator(calendarOrchestrator: CalendarOrchestrator) {
        presenter = CalendarDetailsPresenter(this, calendarOrchestrator)
    }

    fun loadData() {
        calendarInfo?.let { info ->
            presenter?.loadEvents(info)
        }
    }

    fun loadCalendarEventsByName(name: String) {
        presenter?.loadEventsByCalendarName(name)
    }

    fun release() {
        presenter?.unSubscribe()
    }
    //endregion

    //region CalendarDetailsContract.View
    override fun showEvents(events: List<CalendarEvent>) {
        calendarDetailsEmptyViewContainer.visibility = GONE
        adapter.data = events.toMutableList()
    }

    override fun showNoEventsResult() {
        calendarDetailsEmptyViewContainer.visibility = VISIBLE
    }

    override fun showLoading() {
        calendarDetailsLoader.visibility = VISIBLE
    }

    override fun hideLoading() {
        calendarDetailsLoader.visibility = GONE
    }
    //endregion

    //region Utility API
    private fun initAdapterForRecyclerView() {
        val margin = resources.getDimension(R.dimen.margin_all_default).toInt()
        calendarDetailsEventRecyclerView.addItemDecoration(MarginItemDecoration(margin))
        calendarDetailsEventRecyclerView.adapter = adapter
    }
    //endregion
}