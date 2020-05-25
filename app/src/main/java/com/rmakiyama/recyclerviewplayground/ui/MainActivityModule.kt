package com.rmakiyama.recyclerviewplayground.ui

import com.rmakiyama.recyclerviewplayground.di.AssistedInjectModule
import com.rmakiyama.recyclerviewplayground.di.PageScope
import com.rmakiyama.recyclerviewplayground.ui.detail.DetailFragment
import com.rmakiyama.recyclerviewplayground.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @PageScope
    @ContributesAndroidInjector(modules = [AssistedInjectModule::class])
    abstract fun contributeHomeFragment(): HomeFragment

    @PageScope
    @ContributesAndroidInjector(modules = [AssistedInjectModule::class])
    abstract fun contributeDetailFragment(): DetailFragment
}
