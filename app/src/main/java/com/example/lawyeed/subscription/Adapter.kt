package com.example.lawyeed.subscription

import Beans.Subscription
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.R
import com.example.lawyeed.one_case

class Adapter(val subscriptions:List<Subscription>): RecyclerView.Adapter<SubscriptionListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionListViewHolder {

        val layoutInflater= LayoutInflater.from(parent.context)
        return SubscriptionListViewHolder(layoutInflater.inflate(R.layout.item_subscription,parent,false))
    }

    override fun onBindViewHolder(holder: SubscriptionListViewHolder, position: Int) {
        val subscription = subscriptions[position]
        holder.render(subscription)
    }

    override fun getItemCount(): Int = subscriptions.size
}