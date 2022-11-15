package com.example.lawyeed.notification

import Beans.Notification
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.R

class NotificationListViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val title = view.findViewById<TextView>(R.id.text_title)
    val description = view.findViewById<TextView>(R.id.text_description)

    fun render(notification: Notification){
        title.text = notification.title.toString()
        description.text = notification.description.toString();


    }

}