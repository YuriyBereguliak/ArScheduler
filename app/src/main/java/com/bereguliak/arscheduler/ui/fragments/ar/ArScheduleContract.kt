package com.bereguliak.arscheduler.ui.fragments.ar

import android.content.Context
import com.google.ar.core.Config
import com.google.ar.core.Session

interface ArScheduleContract {
    interface View {

    }

    interface Presenter {
        fun initDatabase(context: Context, config: Config, session: Session)
    }
}