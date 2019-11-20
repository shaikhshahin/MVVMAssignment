package com.shahin.assignmentinfomvvm.ui.activity.main


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shahin.assignmentinfomvvm.App
import com.shahin.assignmentinfomvvm.data.db.database.User


import com.shahin.assignmentinfomvvm.data.network.model.UserData
import com.shahin.assignmentinfomvvm.data.network.model.UserResponse
import com.shahin.assignmentinfomvvm.data.network.services.UserService
import com.shahin.mvvmassignment.data.db.database.DatabaseClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


/**
 * Created by Shahin on 19/11/2019.
 */
class MainViewModel internal constructor(private val userService: UserService) : ViewModel() {

    internal val userDatas: MutableLiveData<List<UserData>>
    internal val loadingStatus: MutableLiveData<Boolean>


    init {
        userDatas = MutableLiveData()
        loadingStatus = MutableLiveData()
    }

    internal fun loadUserDatasNetwork() {
        setIsLoading(true)

        val UserDataCall = userService.userApi.allUser
        UserDataCall.enqueue(UserDataCallback())


    }


    internal fun loadUserDataLocal() {
        setIsLoading(true)

        val name = "Beavers "
        val image =
            "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
        val name1 = "Flag"
        val image1 = "http://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png"
        val name2 = "Housing"
        val image2 = "http://icons.iconarchive.com/icons/iconshock/alaska/256/Igloo-icon.png"
        val name3 = "Kittens..."
        val image3 = "http://www.donegalhimalayans.com/images/That%20fish%20was%20this%20big.jpg"
        val UserDatas = ArrayList<UserData>()


        UserDatas.add(UserData(name, image, name))
        UserDatas.add(UserData(name1, image1, name1))
        UserDatas.add(UserData(name2, image2, name2))
        UserDatas.add(UserData(name3, image3, name3))


        setUserDatas(UserDatas)


    }


    internal fun showEmptyList() {
        setUserDatas(emptyList())
    }

    private fun setIsLoading(loading: Boolean) {
        loadingStatus.postValue(loading)
    }

    private fun setUserDatas(UserDatas: List<UserData>?) {
        setIsLoading(false)
        userDatas.postValue(UserDatas)
    }

    /**
     * Callback
     */
    private inner class UserDataCallback : Callback<UserResponse> {

        override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
            val UserDataResponse = response.body()

            if (UserDataResponse != null) {
                setUserDatas(UserDataResponse.userData)
                //adding to database
                App.getInstance()?.let { DatabaseClient.getInstance(it) }?.appDatabase
                    ?.userDao()?.insert(UserDataResponse.userData)
            } else {
                setUserDatas(emptyList())
            }
        }

        override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            setUserDatas(emptyList())

        }
    }
}
