package com.minx.daggerapp

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.minx.daggerapp.data.DataManager
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by Mayur on 19/3/18.
 */
abstract class BaseActivity: AppCompatActivity() {

    @Inject
    lateinit var dataManager: DataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}
