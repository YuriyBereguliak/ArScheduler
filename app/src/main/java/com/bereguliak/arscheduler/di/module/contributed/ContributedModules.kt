package com.bereguliak.arscheduler.di.module.contributed

import com.bereguliak.arscheduler.di.module.connection.ConnectionModule
import com.bereguliak.arscheduler.di.module.loading.LoadingModule
import dagger.Module

@Module(
    includes = [ConnectionModule::class, LoadingModule::class]
)
abstract class ContributedModules