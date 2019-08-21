package com.bereguliak.arscheduler.di.module

import com.bereguliak.arscheduler.domain.calendar.location.CalendarLocationOrchestrator
import com.bereguliak.arscheduler.domain.calendar.location.DefaultCalendarLocationOrchestrator
import com.bereguliak.arscheduler.domain.user.DefaultUserOrchestrator
import com.bereguliak.arscheduler.domain.user.UserOrchestrator
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainModule {
    @Binds
    @Singleton
    fun bindsCalendarLocationOrchestrator(orchestrator: DefaultCalendarLocationOrchestrator): CalendarLocationOrchestrator

    @Binds
    fun bindsUserOrchestrator(orchestrator: DefaultUserOrchestrator): UserOrchestrator
}