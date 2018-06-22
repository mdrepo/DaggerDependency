package com.minx.daggerapp.di.module

import com.minx.daggerapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Mayur on 19/3/18.
 *
 * Modules are providers of dependencies
 * Return type of methods are used to decide which method provides what dependency.
 *
 * When this class is asked for dependency for context, it will provide activity
 *
 * This is similar to Factory class
 */
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity
}