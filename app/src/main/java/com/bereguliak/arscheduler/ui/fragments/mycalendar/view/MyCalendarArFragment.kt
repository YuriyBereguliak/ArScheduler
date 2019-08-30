package com.bereguliak.arscheduler.ui.fragments.mycalendar.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bereguliak.arscheduler.ui.fragments.mycalendar.MyCalendarContract
import com.google.ar.sceneform.ux.ArFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MyCalendarArFragment @Inject constructor() : ArFragment(), MyCalendarContract.View {

    @Inject
    internal lateinit var presenter: MyCalendarContract.Presenter

    //region ArFragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    //endregion
}