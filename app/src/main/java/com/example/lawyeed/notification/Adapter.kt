package com.example.lawyeed.notification

import Beans.Notification
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.R
import com.example.lawyeed.one_case

class Adapter(val notifications:List<Notification>): RecyclerView.Adapter<NotificationListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationListViewHolder {

        val layoutInflater= LayoutInflater.from(parent.context)
        return NotificationListViewHolder(layoutInflater.inflate(R.layout.item_notification,parent,false))
    }

    override fun onBindViewHolder(holder: NotificationListViewHolder, position: Int) {
        val post = notifications[position]
        holder.render(post)
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                var intent: Intent = Intent(holder.itemView.context, one_case::class.java)
                intent.putExtra("id",post.consultId)
                holder.itemView.context.startActivity(intent)
            }
        })
    }

    override fun getItemCount(): Int = notifications.size
}