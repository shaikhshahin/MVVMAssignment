package com.shahin.assignmentinfomvvm.data.db.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

import com.shahin.assignmentinfomvvm.data.db.entity.LogClass

/**
 * Created by Shahin on 19/11/2019.
 */

@Dao
interface LogDAO {

    @get:Query("SELECT * FROM LogClass")
    val all: List<LogClass>

    @Query("DELETE FROM LogClass")
    fun dropTable()

    @Insert
    fun insertAll(vararg logs: LogClass)

    @Delete
    fun delete(log: LogClass)
}
