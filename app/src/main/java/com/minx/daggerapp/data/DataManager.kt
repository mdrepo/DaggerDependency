package com.minx.daggerapp.data

import android.content.Context
import android.content.res.Resources
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Mayur on 13/3/18.
 * Inject - Dependency application
 */
@Singleton
class DataManager @Inject
constructor(private val mContext: Context,
            private val mDbHelper: DbHelper,
            private val mSharedPrefsHelper: SharedPrefsHelper) {

    val accessToken: String?
        get() = mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, "")

    fun saveAccessToken(accessToken: String) {
        mSharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, accessToken)
    }

    @Throws(Exception::class)
    fun createUser(user: User): Long? = mDbHelper.insertUser(user)

    @Throws(Resources.NotFoundException::class, NullPointerException::class)
    fun getUser(userId: Long): User = mDbHelper.getUser(userId)
}