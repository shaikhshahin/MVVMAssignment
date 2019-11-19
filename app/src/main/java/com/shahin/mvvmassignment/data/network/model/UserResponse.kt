package com.shahin.assignmentinfomvvm.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.shahin.assignmentinfomvvm.utils.AppConstants

/**
 * Created by Shahin on 19/11/2019.
 */

class UserResponse {

    @Expose
    @SerializedName("rows")
    val userData: List<UserData>? = null
}
