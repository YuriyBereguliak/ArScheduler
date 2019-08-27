package com.bereguliak.arscheduler.di.module

import com.bereguliak.arscheduler.utilities.network.DefaultNetworkUtils
import com.bereguliak.arscheduler.utilities.network.NetworkUtils
import dagger.Binds
import dagger.Module

@Module
interface UtilityModule {
    @Binds
    fun bindsNetworkUtils(utils: DefaultNetworkUtils): NetworkUtils
}