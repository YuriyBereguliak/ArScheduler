package com.bereguliak.arscheduler.di.module.app

import android.content.Context
import com.bereguliak.arscheduler.App
import com.bereguliak.arscheduler.di.AppContext
import com.bereguliak.arscheduler.di.module.data.local.LocalRepositoryModule
import com.bereguliak.arscheduler.di.module.google.GoogleApiModule
import dagger.Module
import dagger.Provides

@Module(includes = [LocalRepositoryModule::class, GoogleApiModule::class])
class AppModule {

    @AppContext
    @Provides
    fun provideContext(app: App): Context = app.applicationContext
}