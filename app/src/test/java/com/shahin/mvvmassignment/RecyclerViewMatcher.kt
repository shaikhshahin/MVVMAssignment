package com.shahin.mvvmassignment

import android.content.res.Resources
import android.view.View

import androidx.recyclerview.widget.RecyclerView

import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Test
import org.junit.runner.Description

class RecyclerViewMatcher(private val recyclerViewId: Int) {

    fun atPosition(position: Int): Matcher<View> {
        return atPositionOnView(position, -1)
    }

    fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {

            internal var resources: Resources? = null
            internal var childView: View? = null
            override fun describeTo(description: org.hamcrest.Description) {
                var idDescription = Integer.toString(recyclerViewId)
                if (this.resources != null) {
                    try {
                        idDescription = this.resources!!.getResourceName(recyclerViewId)
                    } catch (var4: Resources.NotFoundException) {
                        idDescription =
                            String.format("%s (resource name not found)", recyclerViewId)
                    }

                }

                description.appendText("RecyclerView with id: $idDescription at position: $position")

            }


            public override fun matchesSafely(view: View): Boolean {

                this.resources = view.resources

                if (childView == null) {
                    val recyclerView =
                        view.rootView.findViewById<View>(recyclerViewId) as RecyclerView
                    if (recyclerView != null && recyclerView.id == recyclerViewId) {
                        val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                        if (viewHolder != null) {
                            childView = viewHolder.itemView
                        }
                    } else {
                        return false
                    }
                }

                if (targetViewId == -1) {
                    return view === childView
                } else {
                    val targetView = childView!!.findViewById<View>(targetViewId)
                    return view === targetView
                }
            }
        }
    }
}