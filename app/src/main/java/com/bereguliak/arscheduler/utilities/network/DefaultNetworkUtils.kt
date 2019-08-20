package com.bereguliak.arscheduler.utilities.network

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class DefaultNetworkUtils @Inject constructor(private val context: Context) : NetworkUtils {
    //region NetworkUtils
    override fun isConnectionAvailable(): Boolean {
        val activeNetworkInfo = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
    //endregion
}