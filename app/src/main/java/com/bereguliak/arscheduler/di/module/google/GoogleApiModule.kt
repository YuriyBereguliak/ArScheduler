package com.bereguliak.arscheduler.di.module.google

import android.content.Context
import com.bereguliak.arscheduler.data.local.user.UserLocalRepository
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.client.util.ExponentialBackOff
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import dagger.Module
import dagger.Provides

@Module
class GoogleApiModule {

    @Provides
    fun provideJsonFactory(): JsonFactory = GsonFactory.getDefaultInstance()

    @Provides
    fun provideTransport(): HttpTransport = AndroidHttp.newCompatibleTransport()

    @Provides
    fun provideCredentials(context: Context,
                           userLocalRepository: UserLocalRepository): GoogleAccountCredential {
        return GoogleAccountCredential.usingOAuth2(context, setOf(CalendarScopes.CALENDAR_READONLY))
                .setBackOff(ExponentialBackOff())
                .setSelectedAccountName(userLocalRepository.loadUserName())
    }

    @Provides
    fun provideCalendar(jsonFactory: JsonFactory,
                        transport: HttpTransport,
                        credential: GoogleAccountCredential): Calendar {
        return Calendar.Builder(transport, jsonFactory, credential)
                .setApplicationName("BereguliakArScheduler")
                .build()
    }
}