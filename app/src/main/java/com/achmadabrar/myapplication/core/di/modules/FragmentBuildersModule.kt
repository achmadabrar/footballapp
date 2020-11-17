package com.achmadabrar.myapplication.core.di.modules

import com.achmadabrar.myapplication.ui.fragment.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeListLeagueFragment(): ListLeagueFragment

    @ContributesAndroidInjector
    abstract fun contributesDeatailLeagueFragment(): DetailLeagueFragment

    @ContributesAndroidInjector
    abstract fun contributesNextMatchFragment(): NextMatchFragment

    @ContributesAndroidInjector
    abstract fun contributesPreviousMatchFragment(): PreviousMatchFragment

    @ContributesAndroidInjector
    abstract fun contributesDetailMatchFragment(): DetailMatchFragment

    @ContributesAndroidInjector
    abstract fun contributesOptionFragment(): OptionFragment

    @ContributesAndroidInjector
    abstract fun contributesFavoriteMatchFragment(): FavoriteMatchFragment

    @ContributesAndroidInjector
    abstract fun contributeMatchFragment(): MatchFragment
}