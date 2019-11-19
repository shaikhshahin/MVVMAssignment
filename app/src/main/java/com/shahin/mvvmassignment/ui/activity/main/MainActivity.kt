package com.shahin.assignmentinfomvvm.ui.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.shahin.assignmentinfomvvm.data.DataManager
import com.shahin.assignmentinfomvvm.data.network.model.UserData
import com.shahin.assignmentinfomvvm.ui.activity.details.DetailsActivity

import butterknife.BindView
import butterknife.ButterKnife
import com.shahin.assignmentinfomvvm.utils.NetworkUtils
import com.shahin.mvvmassignment.R

/**
 * Created by Shahin on 19/11/2019.
 */

class MainActivity : AppCompatActivity(), UserAdapter.OnMovieAdapter {

    internal lateinit var userAdapter: UserAdapter

    internal var recyclerView: RecyclerView? = null

    @BindView(R.id.progress_bar)
    internal var progressBar: ProgressBar? = null

    @BindView(R.id.empty_view)
    internal var emptyView: TextView? = null

    internal lateinit var viewModel: MainViewModel


    private fun createViewModel(): MainViewModel {
        val factory = MainViewModelFactory(DataManager.instance.userService)
        return ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)


        recyclerView = findViewById(R.id.recycler_view) as RecyclerView


        recyclerView?.layoutManager = LinearLayoutManager(this)


        viewModel = createViewModel()

        viewModel.loadingStatus.observe(this, LoadingObserver())
        viewModel.userDatas.observe(this, UserObserver())

        var networkStatus : Boolean = NetworkUtils.isNetworkConnected(this)

        if(networkStatus==true)
        {
        viewModel.loadUserDatasNetwork()
        }
        else {
            viewModel.loadUserDataLocal()
        }

        userAdapter = UserAdapter(this)

        recyclerView?.adapter = userAdapter


    }


    override fun onMovieClicked(userData: UserData) {

        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("user", userData)
        startActivity(intent)

    }

    override fun onPointerCaptureChanged(hasCapture: Boolean) {

    }

    //Observer
    private inner class LoadingObserver : Observer<Boolean> {

        override fun onChanged(isLoading: Boolean?) {
            if (isLoading == null) return

            if (isLoading) {
                progressBar?.visibility = View.VISIBLE
            } else {
                progressBar?.visibility = View.GONE
            }
        }
    }

    private inner class UserObserver : Observer<List<UserData>> {

        override fun onChanged(userData: List<UserData>?) {
            if (userData == null) return
            userAdapter.setItems(userData)

            if (userData.isEmpty()) {
                emptyView?.visibility = View.VISIBLE
            } else {
                emptyView?.visibility = View.GONE
            }
        }
    }
}
