package com.shahin.assignmentinfomvvm.ui.activity.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.shahin.assignmentinfomvvm.App
import com.shahin.assignmentinfomvvm.data.db.database.User
import com.shahin.assignmentinfomvvm.data.network.model.UserData
import com.shahin.assignmentinfomvvm.utils.NetworkUtils
import com.shahin.mvvmassignment.R
import com.shahin.mvvmassignment.data.db.database.DatabaseClient


import java.util.ArrayList


/**
 * Created by Shahin on 19/11/2019.
 */
class UserAdapter(private val mListener: OnMovieAdapter) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var mItems: List<UserData>? = null

    interface OnMovieAdapter {
        fun onMovieClicked(userData: UserData)
    }

    init {
        mItems = ArrayList()
    }

    fun setItems(items: List<UserData>) {
        mItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userData = getItem(position)

        val UserList:List<User>? = App.getInstance()?.let { DatabaseClient.getInstance(it) }?.appDatabase

            ?.userDao()
            ?.getAll()


        Log.e("List", UserList.toString())


        holder.setOnClickListener(userData)
        holder.setTitle(userData.title)
        holder.setImage(userData.image)
        userData.description?.let { holder.setDescription(it) }
    }

    override fun getItemCount(): Int {
        return mItems!!.size
    }

    private fun getItem(position: Int): UserData {
        return mItems!![position]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        internal var image: AppCompatImageView? = null
        internal var title: TextView? = null
        internal var desc: TextView? = null


        init {

            title = itemView.findViewById(R.id.title) as TextView
            desc = itemView.findViewById(R.id.desc) as TextView
            image = itemView.findViewById(R.id.image) as AppCompatImageView

        }

        fun setTitle(title: String?) {

            this.title?.text= title

        }

        fun setImage(imageUrl: String?) {
            image?.let { Glide.with(itemView.context).load(imageUrl).into(it) }
        }

        fun setDescription(description: String) {
            this.desc?.text  =  description
        }

        fun setOnClickListener(userData: UserData) {
            itemView.tag = userData
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            mListener.onMovieClicked(view.tag as UserData)
        }
    }
}
