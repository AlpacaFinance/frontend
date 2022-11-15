package com.example.lawyeed.subscription

import Beans.Subscription
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.R

class SubscriptionListViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val name = view.findViewById<TextView>(R.id.text_sub_name)
    val description = view.findViewById<TextView>(R.id.text_sub_description)

    fun render(subscription:Subscription){
        name.text = subscription.name.toString()  + " $" + subscription.price.toString()
        description.text = subscription.description.toString()
    }

}