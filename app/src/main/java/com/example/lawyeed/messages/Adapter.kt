package com.example.lawyeed.messages

import Beans.Messages
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.R

import com.example.lawyeed.one_lawyer

class Adapter(val messages:List<Messages>, val personId: Int): RecyclerView.Adapter<MessagesListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesListViewHolder {

        val layoutInflater= LayoutInflater.from(parent.context)
        return MessagesListViewHolder(layoutInflater.inflate(R.layout.item_message,parent,false))
    }

    override fun onBindViewHolder(holder: MessagesListViewHolder, position: Int) {
        val message = messages[position]
        holder.render(message, personId)

    }

    override fun getItemCount(): Int = messages.size
}