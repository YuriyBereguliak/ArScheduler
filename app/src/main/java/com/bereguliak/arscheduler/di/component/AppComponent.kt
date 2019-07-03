package com.bereguliak.arscheduler.di.component

import com.bereguliak.arscheduler.App
import com.bereguliak.arscheduler.di.module.app.AppModule
import com.bereguliak.arscheduler.di.module.contributed.ContributedModules
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
        ContributedModules::class]
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