package com.bereguliak.arscheduler.model

import com.google.api.services.calendar.model.CalendarListEntry

data class CalendarModel(var map: HashMap<String, CalendarInfo>) {

    //region CalendarModel
    fun size() = map.size

    fun remove(id: String) = map.remove(id)

    fun reset(list: List<CalendarListEntry>) {
        map.clear()
        list.forEach { data ->
            map.put(data.id, CalendarInfo(data.id, data.summary))
        }
    }
    //endregion
}