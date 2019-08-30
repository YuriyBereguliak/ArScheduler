package com.bereguliak.arscheduler.di.component

import com.bereguliak.arscheduler.App
import com.bereguliak.arscheduler.di.module.DomainModule
import com.bereguliak.arscheduler.di.module.GoogleApiModule
import com.bereguliak.arscheduler.di.module.UtilityModule
import com.bereguliak.arscheduler.di.module.app.AppModule
import com.bereguliak.arscheduler.di.module.connection.ConnectionModule
import com.bereguliak.arscheduler.di.module.data.local.LocalRepositoryModule
import com.bereguliak.arscheduler.di.module.details.CalendarDetailsModule
import com.bereguliak.arscheduler.di.module.loading.LoadingModule
import com.bereguliak.arscheduler.di.module.mycalendar.MyCalendarModule
import com.bereguliak.arscheduler.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@AppScope
@Component(
        modules = [
            AndroidInjectionModule::class,
            AndroidSupportInjectionModule::class,
            AppModule::class,
            LocalRepositoryModule::class,
            ConnectionModule::class,
            CalendarDetailsModule::class,
            MyCalendarModule::class,
            LoadingModule::class,
            DomainModule::class,
            GoogleApiModule::class,
            UtilityModule::class]
)
@Singleton
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }
}