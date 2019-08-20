package com.bereguliak.arscheduler.model

import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable

data class CalendarLocation(val id: String, val summary: String, val backgroundColor: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString()
    )

    fun color() = try {
        Color.parseColor(backgroundColor)
    } catch (e: IllegalArgumentException) {
        0
    }

    //region Parcelable
    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(summary)
        writeString(backgroundColor)
    }
    //endregion

    //region Utility strcuture
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CalendarLocation> = object : Parcelable.Creator<CalendarLocation> {
            override fun createFromParcel(source: Parcel): CalendarLocation = CalendarLocation(source)
            override fun newArray(size: Int): Array<CalendarLocation?> = arrayOfNulls(size)
        }
    }
    //endregion
}