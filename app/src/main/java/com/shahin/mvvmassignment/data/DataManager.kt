package com.shahin.assignmentinfomvvm.data


import com.preference.PowerPreference
import com.preference.Preference
import com.shahin.assignmentinfomvvm.App
import com.shahin.assignmentinfomvvm.data.db.database.LogDatabase
import com.shahin.assignmentinfomvvm.data.network.services.UserService

/**
 * Created by Shahin on 19/11/2019.
 */

class DataManager private constructor()// This class is not publicly instantiable
{

    val prefs: Preference
        get() = PowerPreference.defult()

    val logDatabse: LogDatabase?
        get() = App.getInstance()?.let { LogDatabase.getInstance(it) }

    val userService: UserService
        get() = UserService.getInstance()

    companion object {

        private var sInstance: DataManager? = null

        val instance: DataManager
            @Synchronized get() {
                if (sInstance == null) {
                    sInstance = DataManager()
                }
                return sInstance!!
            }
    }

}
