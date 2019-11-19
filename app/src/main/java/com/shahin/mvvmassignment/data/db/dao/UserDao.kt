package com.shahin.assignmentinfomvvm.data.db.database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete



/**
 * Created by Shahin on 19/11/2019.
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM User")
     fun getAll(): List<User>

    @Insert
    fun insert(task: User)

    @Delete
    fun delete(task: User)

    @Update
    fun update(task: User)

}
