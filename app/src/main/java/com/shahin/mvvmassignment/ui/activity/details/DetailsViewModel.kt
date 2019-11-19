package com.shahin.assignmentinfomvvm.ui.activity.details

import android.content.Intent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.shahin.assignmentinfomvvm.data.network.model.UserData


/**
 * Created by Shahin on 10/11/2019.
 */
class DetailsViewModel : ViewModel() {

    var movie: MutableLiveData<UserData>
        internal set

    init {
        this.movie = MutableLiveData()
    }

    fun loadMovieData(intent: Intent) {
        assert(intent.extras != null)
        val movieExtra = intent.extras!!.getParcelable<UserData>("user")

        movie.postValue(movieExtra)
    }
}
