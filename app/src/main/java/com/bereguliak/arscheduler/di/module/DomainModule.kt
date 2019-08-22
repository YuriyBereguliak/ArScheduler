package com.bereguliak.arscheduler.di.module

import com.bereguliak.arscheduler.domain.calendar.location.CalendarOrchestrator
import com.bereguliak.arscheduler.domain.calendar.location.DefaultCalendarOrchestrator
import com.bereguliak.arscheduler.domain.user.DefaultUserOrchestrator
import com.bereguliak.arscheduler.domain.user.UserOrchestrator
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainModule {
    @Binds
    @Singleton
    fun bindsCalendarLocationOrchestrator(orchestrator: DefaultCalendarOrchestrator): CalendarOrchestrator

    @Binds
    fun bindsUserOrchestrator(orchestrator: DefaultUserOrchestrator): UserOrchestrator
}