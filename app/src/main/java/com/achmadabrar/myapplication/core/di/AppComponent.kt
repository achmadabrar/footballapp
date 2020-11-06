package com.achmadabrar.myapplication.core.di

import android.app.Application
import com.achmadabrar.myapplication.core.base.BaseApplication
import com.achmadabrar.myapplication.core.di.modules.ActivityBuildersModule
import com.achmadabrar.myapplication.core.di.modules.FragmentBuildersModule
import com.achmadabrar.myapplication.core.di.modules.NetworkModule
import com.achmadabrar.myapplication.core.di.modules.ViewModelModule
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
        ViewModelModule::class
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