package com.bereguliak.arscheduler.utilities

import com.google.api.client.util.DateTime
import java.text.SimpleDateFormat
import java.util.*

fun getDateTime(dt: DateTime): Date? {
    return try {
        val source = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(dt.toStringRfc3339())
        source
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
