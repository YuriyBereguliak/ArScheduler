package com.bereguliak.arscheduler.ui.fragments.loading.view

import android.support.annotation.LayoutRes
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.core.ui.BaseFragment
import com.bereguliak.arscheduler.ui.fragments.connection.view.ConnectionFragment
import com.bereguliak.arscheduler.ui.fragments.loading.LoadingContract
import com.bereguliak.arscheduler.ui.fragments.loading.presenter.LoadingPresenter

class LoadingFragment : BaseFragment(), LoadingContract.View {

    private val presenter: LoadingContract.Presenter by lazy { LoadingPresenter(this) }

    //region BaseFragment
    @LayoutRes
    override fun getContentViewId() = R.layout.fragment_loading

    override fun initView() {

    }
    //endregion

    //region Utility structure
    companion object {
        @JvmStatic
        fun newInstance() = LoadingFragment()
    }
    //endregion
}