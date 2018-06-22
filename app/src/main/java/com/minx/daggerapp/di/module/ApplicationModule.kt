package com.minx.daggerapp.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.minx.daggerapp.MainActivity
import com.minx.daggerapp.di.qualifiers.ApplicationContext
import com.minx.daggerapp.di.qualifiers.DatabaseInfo
import dagger.Module
import dagger.Provides


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
internal class ApplicationModule(private val mApplication: Application) {
    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }

    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @DatabaseInfo
    internal fun provideDatabaseName(): String {
        return "demo-dagger.db"
    }

    @Provides
    @DatabaseInfo
    internal fun provideDatabaseVersion(): Int {
        return 2
    }

    @Provides
    internal fun provideSharedPrefs(): SharedPreferences {
        return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE)
    }
}