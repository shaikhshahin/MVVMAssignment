package com.shahin.mvvmassignment.ui.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.annotation.SuppressLint
import com.shahin.mvvmassignment.R
import com.shahin.mvvmassignment.ui.fragment.HomeFragment


class MainActivity : AppCompatActivity() {


    internal val fragment1: Fragment =
        HomeFragment()

    internal val fm = supportFragmentManager
    internal var active = fragment1


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentManager.beginTransaction().add(R.id.main_container, fragment1, "1").commit()




    }


}
