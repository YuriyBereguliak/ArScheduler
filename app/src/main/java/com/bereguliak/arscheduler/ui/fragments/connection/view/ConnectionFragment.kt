package com.bereguliak.arscheduler.ui.fragments.connection.view

import android.accounts.AccountManager
import android.app.Activity
import android.content.Intent
import android.support.annotation.LayoutRes
import android.view.View
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.core.ui.BaseFragment
import com.bereguliak.arscheduler.model.CalendarLocation
import com.bereguliak.arscheduler.ui.fragments.connection.ConnectionContract
import com.bereguliak.arscheduler.ui.fragments.connection.adapter.OnUserCalendarClickListener
import com.bereguliak.arscheduler.ui.fragments.connection.adapter.UserCalendarsAdapter
import com.bereguliak.arscheduler.utilities.extensions.toast
import com.google.android.gms.common.AccountPicker
import com.google.api.client.googleapis.extensions.android.accounts.GoogleAccountManager
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_connection.*
import javax.inject.Inject

class ConnectionFragment : BaseFragment(), ConnectionContract.View, OnUserCalendarClickListener {

    @Inject
    lateinit var presenter: ConnectionContract.Presenter

    private val adapter: UserCalendarsAdapter by lazy { UserCalendarsAdapter(this) }

    //region BaseFragment
    @LayoutRes
    override fun getContentViewId() = R.layout.fragment_connection

    override fun initView() {
        AndroidSupportInjection.inject(this)

        userCalendarsRecyclerView.adapter = adapter

        fragmentConnectionGoToArButton.setOnClickListener {
            navigator.showArSchedulerScreen()
        }
        fragmentConnectionGoToMyCalendarArButton.setOnClickListener {
            presenter.findUserCalendar()
        }
        userLogout.setOnClickListener {
            adapter.data = mutableListOf()
            fragmentConnectionGoToArButton.isEnabled = false
            fragmentConnectionGoToMyCalendarArButton.isEnabled = false
            userLogout.visibility = View.GONE
            userNameHint.visibility = View.GONE
            userConnectionStatus.setImageResource(R.drawable.ic_calendar_error)
            presenter.logout()
        }
        userIcon.setOnClickListener {
            presenter.prepareChooseAccount()
        }
        connectionNoNetworkImageView.setOnClickListener {
            presenter.startDownloadDataFromCalendar()
        }

        presenter.loadUserInfo()
    }

    override fun onStop() {
        super.onStop()
        presenter.unSubscribe()
    }
    //endregion

    //region Fragment
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_ACCOUNT_PICKER -> handleSelectedAccountResult(resultCode, data)
            REQUEST_AUTHORIZATION -> handleAccountAuthorization(resultCode)
        }
    }
    //endregion

    //region ConnectionContract.View
    override fun showLoading() {
        userCalendarLoader.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        userCalendarLoader.visibility = View.GONE
    }

    override fun chooseAccount() {
        val intent = AccountPicker.newChooseAccountIntent(null, null, arrayOf(GoogleAccountManager.ACCOUNT_TYPE), true, null, null, null, null)
        startActivityForResult(intent, REQUEST_ACCOUNT_PICKER)
    }

    override fun chooseAccountNotAllowed() {
        toast(R.string.text_connection_logout_from_previous)
    }

    override fun setUserName(user: String) {
        userName.text = user
    }

    override fun accountConnected() {
        userLogout.visibility = View.VISIBLE
        userNameHint.visibility = View.VISIBLE
        userConnectionStatus.setImageResource(R.drawable.ic_calendar_sync)
        presenter.startDownloadDataFromCalendar()
    }

    override fun authorizationRequired(intent: Intent) {
        startActivityForResult(intent, REQUEST_AUTHORIZATION)
    }

    override fun userCalendarsLoaded() {
        fragmentConnectionGoToArButton.isEnabled = true
        fragmentConnectionGoToMyCalendarArButton.isEnabled = true
        userConnectionStatus.setImageResource(R.drawable.ic_calendar_ready)
    }

    override fun showUserCalendarLocations(data: MutableList<CalendarLocation>) {
        adapter.data = data
    }

    override fun showNoNetworkError() {
        connectionNoNetworkImageView.visibility = View.VISIBLE
    }

    override fun hideNoNetworkError() {
        connectionNoNetworkImageView.visibility = View.GONE
    }

    override fun showUserCalendarInfo(calendarLocation: CalendarLocation) {
        navigator.showMyCalendarArScheduler(calendarLocation)
    }
    //endregion

    //region OnUserCalendarClickListener
    override fun onCalendarClickListener(calendar: CalendarLocation) {
        navigator.showMyCalendarArScheduler(calendar)
//        navigator.showCalendarDetailsScreen(calendar)
    }
    //endregion

    //region Utility API
    private fun handleSelectedAccountResult(resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            data.extras?.let { extras ->
                extras.getString(AccountManager.KEY_ACCOUNT_NAME)?.let { userName ->
                    setUserName(userName)
                    accountConnected()
                    presenter.userAccountSelected(userName)
                }
            }
        }
    }

    private fun handleAccountAuthorization(resultCode: Int) {
        when (resultCode) {
            Activity.RESULT_OK -> presenter.startDownloadDataFromCalendar()
            else -> presenter.prepareChooseAccount()
        }
    }
    //endregion

    //region Utility structure
    companion object {
        const val REQUEST_ACCOUNT_PICKER = 1
        const val REQUEST_AUTHORIZATION = 2

        @JvmStatic
        fun newInstance() = ConnectionFragment()
    }
    //endregion
}