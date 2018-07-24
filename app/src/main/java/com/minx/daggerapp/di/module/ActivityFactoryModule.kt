package com.minx.daggerapp.di.module

import com.minx.daggerapp.BaseActivity
import com.minx.daggerapp.Main2Activity
import com.minx.daggerapp.MainActivity
import com.minx.daggerapp.di.component.ActivityComponent
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = arrayOf(ActivityComponent::class))
abstract class ActivityFactoryModule {

    @ContributesAndroidInjector
    abstract fun providesBaseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun providesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun providesMain2Activity(): Main2Activity
}