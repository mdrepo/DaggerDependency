package com.minx.daggerapp

import android.support.v7.app.AppCompatActivity
import com.minx.daggerapp.data.DataManager
import javax.inject.Inject

/**
 * Created by Mayur on 19/3/18.
 */
open class BaseActivity: AppCompatActivity() {

    @Inject
    lateinit var dataManager: DataManager


}