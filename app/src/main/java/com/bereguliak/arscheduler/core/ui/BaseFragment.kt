package com.bereguliak.arscheduler.core.ui

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bereguliak.arscheduler.ui.activity.main.MainNavigator

abstract class BaseFragment : Fragment() {

    protected lateinit var navigator: MainNavigator

    @LayoutRes
    abstract fun getContentViewId(): Int

    abstract fun initView()

    //region Fragment
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity?.let {
            if (it is MainNavigator) navigator = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getContentViewId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    //endregion

}