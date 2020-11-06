package com.achmadabrar.myapplication.core.di.modules

import com.achmadabrar.myapplication.ui.activity.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesHomeActivity(): HomeActivity
}