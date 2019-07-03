package com.bereguliak.arscheduler.di.module.contributed

import com.bereguliak.arscheduler.di.module.connection.ConnectionModule
import dagger.Module

@Module(includes = [ConnectionModule::class])
abstract class ContributedModules