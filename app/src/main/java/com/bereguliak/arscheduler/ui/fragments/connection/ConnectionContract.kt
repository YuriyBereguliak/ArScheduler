package com.bereguliak.arscheduler.ui.fragments.connection

import android.content.Context

interface ConnectionContract {
    interface View {

    }

    interface Presenter {
        fun init(context: Context)
    }
}