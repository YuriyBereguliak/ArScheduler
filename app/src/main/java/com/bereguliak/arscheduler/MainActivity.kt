package com.bereguliak.arscheduler

import android.os.Bundle
import com.bereguliak.arscheduler.core.ui.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}