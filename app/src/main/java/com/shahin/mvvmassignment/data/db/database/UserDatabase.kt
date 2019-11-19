package com.shahin.assignmentinfomvvm.data.db.database



import androidx.room.Database
import androidx.room.RoomDatabase

import com.shahin.assignmentinfomvvm.data.network.model.UserData

/**
 * Created by Shahin on 19/11/2019.
 */
@Database(entities = [UserData::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}