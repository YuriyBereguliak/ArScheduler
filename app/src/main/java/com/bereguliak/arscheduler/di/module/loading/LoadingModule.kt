package com.bereguliak.arscheduler.di.module.loading

import com.bereguliak.arscheduler.ui.fragments.loading.view.LoadingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoadingModule {
    @ContributesAndroidInjector(modules = [LoadingContractModule::class])
    @LoadingScope
    internal abstract fun contributeLoadinfFragment(): LoadingFragment
}