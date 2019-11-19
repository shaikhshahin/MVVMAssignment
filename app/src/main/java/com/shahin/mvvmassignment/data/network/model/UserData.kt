package com.shahin.assignmentinfomvvm.data.network.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.shahin.assignmentinfomvvm.utils.AppConstants

/**
 * Created by Shahin on 19/11/2019.
 */
class UserData : Parcelable {

    @Expose
    @SerializedName("description")
    var description: String? = null

    @Expose
    @SerializedName("imageHref")
    var image: String? = null

    @Expose
    @SerializedName("title")
    var title: String? = null

    protected constructor(`in`: Parcel) {
        description = `in`.readString()
        image = `in`.readString()
        title = `in`.readString()
    }

    constructor(description: String, image: String, title: String) {
        this.description = description
        this.image = image
        this.title = title
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(description)
        parcel.writeString(image)
        parcel.writeString(title)
    }



    companion object CREATOR : Parcelable.Creator<UserData> {
        override fun createFromParcel(parcel: Parcel): UserData {
            return UserData(parcel)
        }

        override fun newArray(size: Int): Array<UserData?> {
            return arrayOfNulls(size)
        }
    }
}
