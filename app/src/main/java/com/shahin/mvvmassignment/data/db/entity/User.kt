package com.shahin.assignmentinfomvvm.data.db.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

import com.shahin.assignmentinfomvvm.data.network.model.UserData

/**
 * Created by Shahin on 19/11/2019.
 */
@Entity
class User {

    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "desc")
    var description: String? = null

    @ColumnInfo(name = "imageurl")
    var imageHref: String? = null
}