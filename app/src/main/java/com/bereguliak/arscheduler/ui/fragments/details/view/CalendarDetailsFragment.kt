package com.bereguliak.arscheduler.ui.fragments.details.view

import android.os.Bundle
import android.support.annotation.LayoutRes
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.core.ui.BaseFragment
import com.bereguliak.arscheduler.ui.fragments.details.CalendarDetailsContract
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CalendarDetailsFragment : BaseFragment(), CalendarDetailsContract.View {

    @Inject
    lateinit var presenter: CalendarDetailsContract.Presenter

    //region BaseFragment
    @LayoutRes
    override fun getContentViewId() = R.layout.fragment_calendar_details

    override fun initView() {
        AndroidSupportInjection.inject(this)
    }
    //endregion

    //region Utility structure
    companion object {

        private const val ARG_CALENDAR_NAME = "com.bereguliak.arscheduler.ui.fragments.details.view.CALENDAR_NAME"

        fun newInstance(calendar: String): CalendarDetailsFragment {
            return CalendarDetailsFragment().apply {
                arguments = Bundle(1).apply {
                    putString(ARG_CALENDAR_NAME, calendar)
                }
            }
        }
    }
    //endregion
}