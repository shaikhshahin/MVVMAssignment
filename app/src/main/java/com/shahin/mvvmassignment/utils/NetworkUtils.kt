package com.shahin.assignmentinfomvvm.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by Shahin on 19/11/2019.
 */

object NetworkUtils {

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}// This utility class is not publicly instantiable
