package com.achmadabrar.myapplication.core.di

import android.app.Application
import com.achmadabrar.myapplication.core.base.BaseApplication
import com.achmadabrar.myapplication.core.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ActivityBuildersModule::class,
        FragmentBuildersModule::class,
        ViewModelModule::class,
        DatabaseModule::class
    ]
)

interface AppComponent: AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        abstract fun application (application: Application): Builder

        fun build(): AppComponent
    }
}