package com.bereguliak.arscheduler.utilities

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentTimezoneOffset(): Int {
    return try {
        val format = SimpleDateFormat("Z", Locale.getDefault()).format(Date())

        val hours = format.substring(0, 3)
        val minutes = format.substring(3, 5)

        hours.convertHoursToSeconds() + minutes.convertMinutesToSeconds()
    } catch (exception: NumberFormatException) {
        0
    }
}

//region Utility API
private fun String.convertHoursToSeconds(): Int = Integer.parseInt(this) * 60 * 60

private fun String.convertMinutesToSeconds(): Int = Integer.parseInt(this) * 60
//endregion