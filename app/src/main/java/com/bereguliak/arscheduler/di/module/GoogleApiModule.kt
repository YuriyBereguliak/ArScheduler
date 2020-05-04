package com.bereguliak.arscheduler.di.module

import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import dagger.Module
import dagger.Provides

@Module
class GoogleApiModule {

    @Provides
    fun provideJsonFactory(): JsonFactory = GsonFactory.getDefaultInstance()

    @Provides
    fun provideTransport(): HttpTransport = AndroidHttp.newCompatibleTransport()
}