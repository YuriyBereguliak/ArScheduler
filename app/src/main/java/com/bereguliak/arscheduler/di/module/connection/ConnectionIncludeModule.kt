package com.bereguliak.arscheduler.di.module.connection

import com.bereguliak.arscheduler.domain.calendar.location.CalendarLocationOrchestrator
import com.bereguliak.arscheduler.domain.calendar.location.DefaultCalendarLocationOrchestrator
import dagger.Binds
import dagger.Module

@Module
interface ConnectionIncludeModule {
    @Binds
    fun bindsCalendarLocationOrchestrator(orchestrator: DefaultCalendarLocationOrchestrator): CalendarLocationOrchestrator
}