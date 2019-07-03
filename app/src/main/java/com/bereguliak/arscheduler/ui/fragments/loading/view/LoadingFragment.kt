package com.bereguliak.arscheduler.ui.fragments.loading.view

import android.support.annotation.LayoutRes
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.core.ui.BaseFragment
import com.bereguliak.arscheduler.ui.fragments.loading.LoadingContract
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_loading.*
import javax.inject.Inject

class LoadingFragment : BaseFragment(), LoadingContract.View {

    @Inject
    lateinit var presenter: LoadingContract.Presenter

    //region BaseFragment
    @LayoutRes
    override fun getContentViewId() = R.layout.fragment_loading

    override fun initView() {
        AndroidSupportInjection.inject(this)
        presenter.startInitialization()
    }

    override fun onStop() {
        super.onStop()
        presenter.unSubscribe()
    }
    //endregion

    //region LoadingContract.View
    override fun showNetworkConnectionCheck() {
        loadingStatusTextView.setText(R.string.loading_status_network_connection_check)
    }

    override fun showDatabaseCheck() {
        loadingStatusTextView.setText(R.string.loading_status_database_check)
    }

    override fun showCalendarConnection() {
        loadingStatusTextView.setText(R.string.loading_status_calendar_connection_check)
    }

    override fun showConnectionScreen() {
        navigator.showConnectionScreen()
    }

    override fun showSchedulerScreen() {
        navigator.showArSchedulerScreen()
    }
    //endregion

    //region Utility structure
    companion object {
        @JvmStatic
        fun newInstance() = LoadingFragment()
    }
    //endregion
}