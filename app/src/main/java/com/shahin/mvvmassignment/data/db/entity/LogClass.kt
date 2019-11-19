package com.shahin.assignmentinfomvvm.data.db.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Shahin on 19/11/2019.
 */

@Entity
class LogClass {

    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0

    @ColumnInfo(name = "Log")
    var log: String? = null

    @ColumnInfo(name = "Class")
    var className: String? = null

    @ColumnInfo(name = "Date")
    var date: String? = null
}
