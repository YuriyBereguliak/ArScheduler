package com.bereguliak.arscheduler.model

data class CalendarInfo(var id: String, var summary: String) {

    //region Utility structure
    companion object {
        const val FIELDS = "id,summary"
        const val FEED_FIELDS = "items($FIELDS)"
    }
    //endregion
}