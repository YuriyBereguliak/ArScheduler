package com.bereguliak.arscheduler.di.module.app

import android.content.Context
import com.bereguliak.arscheduler.App
import com.bereguliak.arscheduler.di.AppContext
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @AppContext
    @Provides
    fun provideContext(app: App): Context = app.applicationContext
}