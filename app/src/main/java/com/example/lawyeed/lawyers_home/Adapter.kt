package com.example.lawyeed.lawyers_home

import Beans.Lawyers
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.R

import com.example.lawyeed.one_lawyer

class Adapter(val lawyers:List<Lawyers>): RecyclerView.Adapter<LawyersHomeListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LawyersHomeListViewHolder {

        val layoutInflater= LayoutInflater.from(parent.context)
        return LawyersHomeListViewHolder(layoutInflater.inflate(R.layout.lawyers_home_item,parent,false))
    }

    override fun onBindViewHolder(holder: LawyersHomeListViewHolder, position: Int) {
        val lawyer = lawyers[position]
        holder.render(lawyer)
        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p: View) {
                val intent = Intent(holder.itemView.context, one_lawyer::class.java)
                intent.putExtra("id", lawyer.id)
                holder.itemView.context.startActivity(intent)
            }
        })
    }

    override fun getItemCount(): Int = lawyers.size
}