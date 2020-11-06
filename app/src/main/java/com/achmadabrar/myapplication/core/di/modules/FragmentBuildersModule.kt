package com.achmadabrar.myapplication.core.di.modules

import com.achmadabrar.myapplication.ui.fragment.DetailLeagueFragment
import com.achmadabrar.myapplication.ui.fragment.HomeFragment
import com.achmadabrar.myapplication.ui.fragment.ListLeagueFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeListLeagueFragment(): ListLeagueFragment

    @ContributesAndroidInjector
    abstract fun contributesDeatailLeagueFragment(): DetailLeagueFragment
}