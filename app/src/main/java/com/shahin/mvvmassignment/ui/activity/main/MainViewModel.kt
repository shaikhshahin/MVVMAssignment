package com.shahin.assignmentinfomvvm.ui.activity.main


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shahin.assignmentinfomvvm.App


import com.shahin.assignmentinfomvvm.data.network.model.UserData
import com.shahin.assignmentinfomvvm.data.network.model.UserResponse
import com.shahin.assignmentinfomvvm.data.network.services.UserService
import com.shahin.mvvmassignment.data.db.database.DatabaseClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




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

        // val name = "Beavers "
        //val image =
        //  "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"

        // val desc = "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony\",\n"


        //UserDatas.add(UserData(name, image, desc))
        //UserDatas.add(UserData(name, image, desc))
        //UserDatas.add(UserData(name, image, desc))
        //setUserDatas(UserDatas)


        val UserList = App.getInstance()?.let { DatabaseClient.getInstance(it) }?.appDatabase

            ?.userDao()
            ?.getAll()


        Log.e("List", UserList.toString())



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
