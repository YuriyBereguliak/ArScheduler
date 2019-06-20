package com.bereguliak.arscheduler.ui.fragments.loading.view

import android.os.Bundle
import android.support.annotation.LayoutRes
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.core.ui.BaseFragment
import com.bereguliak.arscheduler.ui.fragments.loading.LoadingContract
import com.bereguliak.arscheduler.ui.fragments.loading.presenter.LoadingPresenter
import kotlinx.android.synthetic.main.fragment_loading.*

class LoadingFragment : BaseFragment(), LoadingContract.View {

    private val presenter: LoadingContract.Presenter by lazy { LoadingPresenter(this) }

    //region BaseFragment
    @LayoutRes
    override fun getContentViewId() = R.layout.fragment_loading

    override fun initView() {
        presenter.startInitialization()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onSubscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
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