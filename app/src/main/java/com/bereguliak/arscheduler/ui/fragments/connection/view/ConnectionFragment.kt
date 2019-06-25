package com.bereguliak.arscheduler.ui.fragments.connection.view

import android.support.annotation.LayoutRes
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.core.ui.BaseFragment
import com.bereguliak.arscheduler.ui.fragments.connection.ConnectionContract
import com.bereguliak.arscheduler.ui.fragments.connection.presenter.ConnectionPresenter
import kotlinx.android.synthetic.main.fragment_connection.*

class ConnectionFragment : BaseFragment(), ConnectionContract.View {

    private val presenter: ConnectionContract.Presenter  by lazy {
        ConnectionPresenter(this)
    }

    //region BaseFragment
    @LayoutRes
    override fun getContentViewId() = R.layout.fragment_connection

    override fun initView() {
        fragmentConnectionGoToArButton.setOnClickListener {
            navigator.showArSchedulerScreen()
        }
    }
    //endregion

    //region Utility structure
    companion object {
        @JvmStatic
        fun newInstance() = ConnectionFragment()
    }
    //endregion
}