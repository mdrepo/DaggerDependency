package com.minx.daggerapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.minx.daggerapp.R.id.tv_user_info
import com.minx.daggerapp.data.DataManager
import com.minx.daggerapp.data.User
import dagger.android.AndroidInjection
import dagger.android.support.DaggerApplication
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Activity should not know about the injector
        // (application as DaggerApplication).getAp
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        createUser()
        getUser()
        dataManager.saveAccessToken("ASDR12443JFDJF43543J543H3K543")
        val token = dataManager.accessToken
        tv_user_info.text = token
    }

    private fun createUser() {
        try {
            dataManager.createUser(User("Mayur", "1367, Gurgaon, Haryana, India"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getUser() = try {
        val user = dataManager.getUser(1L)
        tv_user_info.setText(user.toString())
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
