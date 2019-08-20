package com.bereguliak.arscheduler.ui.activity.main

import android.support.annotation.LayoutRes
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.core.ui.BaseActivity
import com.bereguliak.arscheduler.model.connection.CalendarLocation
import com.bereguliak.arscheduler.ui.fragments.ar.view.ArScheduleFragment
import com.bereguliak.arscheduler.ui.fragments.connection.view.ConnectionFragment
import com.bereguliak.arscheduler.ui.fragments.details.view.CalendarDetailsFragment
import com.bereguliak.arscheduler.ui.fragments.loading.view.LoadingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainNavigator {

    //region BaseActivity
    @LayoutRes
    override fun getContentViewId() = R.layout.activity_main

    override fun initView() {
        showLoadingScreen()
    }
    //endregion

    //region MainNavigator
    override fun showLoadingScreen() {
        replaceFragment(fragmentContainer.id, LoadingFragment.newInstance(), false)
    }

    override fun showConnectionScreen() {
        replaceFragment(fragmentContainer.id, ConnectionFragment.newInstance(), false)
    }

    override fun showCalendarDetailsScreen(calendar: CalendarLocation) {
        replaceFragment(fragmentContainer.id, CalendarDetailsFragment.newInstance(calendar), true)
    }

    override fun showArSchedulerScreen() {
        replaceFragment(fragmentContainer.id, ArScheduleFragment.newInstance(), true)
    }

    override fun showSettingsScreen() {

    }
    //endregion
}