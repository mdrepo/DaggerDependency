package com.minx.daggerapp.di.component

import com.minx.daggerapp.DIApp
import com.minx.daggerapp.di.module.ActivityFactoryModule
import com.minx.daggerapp.di.module.ApplicationModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


/**
 * Created by Mayur on 13/3/18.
 * Dagger builds the dependency graph using the component classes. Wires every thing together
 *
 * This is like the xml file from Spring
 *
 * For this Application component to be built, it needs dependencies
 * provided by Application Module
 */
@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        ActivityFactoryModule::class,
        AndroidInjectionModule::class
))
interface ApplicationComponent: AndroidInjector<DIApp> {
    // DIApp is the class that needs the dependencies, it is the consumer
}

/**
*
* Application Component
* \
* needs
*   \
*   Dagger App
*       \
*       needs
*         \
*         Data Manger
*
*
*/


/**
 * Advice: Make use of Dagger2 Lazy<> especially in dependencies initialised in
 * Application but used later.
 */