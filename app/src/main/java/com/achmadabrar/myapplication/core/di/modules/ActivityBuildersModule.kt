package com.achmadabrar.myapplication.core.di.modules

import com.achmadabrar.myapplication.ui.activity.DetailMatchActivity
import com.achmadabrar.myapplication.ui.activity.HomeActivity
import com.achmadabrar.myapplication.ui.activity.MatchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun contributesMatchActivity(): MatchActivity

    @ContributesAndroidInjector
    abstract fun contributesDetailMatchActivity(): DetailMatchActivity
}