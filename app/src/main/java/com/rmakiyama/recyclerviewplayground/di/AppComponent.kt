package com.rmakiyama.recyclerviewplayground.di

import android.app.Application
import com.rmakiyama.recyclerviewplayground.App
import com.rmakiyama.recyclerviewplayground.data.RepositoryModule
import com.rmakiyama.recyclerviewplayground.ui.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        MainActivityModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}
