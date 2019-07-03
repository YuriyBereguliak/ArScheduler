package com.bereguliak.arscheduler.di.module.data.local

import com.bereguliak.arscheduler.data.local.user.DefaultUserLocalRepository
import com.bereguliak.arscheduler.data.local.user.UserLocalRepository
import dagger.Binds
import dagger.Module

@Module
interface LocalRepositoryModule {
    @Binds
    fun bindsUserLocalRepository(repository: DefaultUserLocalRepository): UserLocalRepository
}