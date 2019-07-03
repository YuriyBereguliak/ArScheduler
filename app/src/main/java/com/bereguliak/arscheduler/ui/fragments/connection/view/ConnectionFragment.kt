package com.bereguliak.arscheduler.ui.fragments.connection.view

import android.support.annotation.LayoutRes
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.core.ui.BaseFragment
import com.bereguliak.arscheduler.ui.fragments.connection.ConnectionContract
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_connection.*
import javax.inject.Inject

class ConnectionFragment : BaseFragment(), ConnectionContract.View {

    @Inject
    lateinit var presenter: ConnectionContract.Presenter

    //region BaseFragment
    @LayoutRes
    override fun getContentViewId() = R.layout.fragment_connection

    override fun initView() {
        AndroidSupportInjection.inject(this)

        context?.let {
            presenter.init(it)
        }

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