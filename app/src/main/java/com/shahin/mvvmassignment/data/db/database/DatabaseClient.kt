package com.shahin.mvvmassignment.data.db.database

import android.content.Context
import androidx.room.Room
import com.shahin.assignmentinfomvvm.data.db.database.UserDatabase


class DatabaseClient private constructor(private val mCtx: Context) {

    //our app database object
    val appDatabase: UserDatabase

    init {

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, UserDatabase::class.java!!, "UserDB").build()
    }

    companion object {
        private var mInstance: DatabaseClient? = null

        @Synchronized
        fun getInstance(mCtx: Context): DatabaseClient {
            if (mInstance == null) {
                mInstance = DatabaseClient(mCtx)
            }
            return mInstance as DatabaseClient
        }
    }
}