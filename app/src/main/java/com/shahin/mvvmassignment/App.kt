package com.shahin.assignmentinfomvvm

import android.app.Application
import android.content.Context


/**
 * Created by Shahin on 19/11/2019.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        fun getInstance(): Context? {

            return instance
        }

        var instance: App? = null
            private set
    }

}
