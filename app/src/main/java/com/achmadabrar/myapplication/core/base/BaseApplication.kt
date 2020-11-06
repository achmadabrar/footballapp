package com.achmadabrar.myapplication.core.base

import android.content.Context
import androidx.multidex.MultiDex
import com.achmadabrar.myapplication.core.di.DaggerAppComponent
//import com.achmadabrar.myapplication.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


open class BaseApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}