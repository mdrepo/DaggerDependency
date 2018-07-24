package com.minx.daggerapp

import android.app.Activity
import android.app.Application
import com.minx.daggerapp.data.DataManager
import com.minx.daggerapp.di.component.ApplicationComponent
import com.minx.daggerapp.di.component.DaggerApplicationComponent
import com.minx.daggerapp.di.module.ApplicationModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


/**
 * Created by Mayur on 13/3/18.
 */
class DIApp: Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    lateinit var applicationComponent: ApplicationComponent

    @Inject
    internal lateinit var dataManager: DataManager

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }
}