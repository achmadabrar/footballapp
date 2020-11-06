package com.achmadabrar.myapplication.ui.viewmodel

import com.achmadabrar.myapplication.core.base.BaseViewModel
import com.achmadabrar.myapplication.data.networks.FootbalScheduleApi
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val scheduleApi: FootbalScheduleApi
) : BaseViewModel<HomeViewModel>() {

    init {

    }


}