package com.achmadabrar.myapplication.core.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.achmadabrar.myapplication.core.di.ViewModelFactory
import com.achmadabrar.myapplication.core.di.ViewModelKey
import com.achmadabrar.myapplication.ui.viewmodel.ListLeagueViewModel
import com.achmadabrar.myapplication.ui.viewmodel.MatchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ListLeagueViewModel::class)
    internal abstract fun listLeagueViewModel(viewModel: ListLeagueViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MatchViewModel::class)
    internal abstract fun matchViewModel(viewModel: MatchViewModel): ViewModel
}