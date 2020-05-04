package com.bereguliak.arscheduler.utilities.extensions

import java.util.*

fun Calendar.setStartOfTheDay() {
    apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
}

fun Calendar.setEndOfTheDay() {
    apply {
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 59)
        set(Calendar.MILLISECOND, 0)
    }
}
