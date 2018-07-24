package com.minx.daggerapp.di.component

import com.minx.daggerapp.BaseActivity
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(AndroidInjectionModule::class))
interface ActivityComponent: AndroidInjector<BaseActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<BaseActivity>()
}