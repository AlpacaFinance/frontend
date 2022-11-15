package com.example.lawyeed.cases

import Beans.Cases
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.R
import com.example.lawyeed.one_case

class Adapter(val cases:List<Cases>): RecyclerView.Adapter<CasesListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasesListViewHolder {

        val layoutInflater= LayoutInflater.from(parent.context)
        return CasesListViewHolder(layoutInflater.inflate(R.layout.item_cases,parent,false))
    }

    override fun onBindViewHolder(holder: CasesListViewHolder, position: Int) {
        val case = cases[position]
        holder.render(case)
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

                var intent:Intent = Intent(holder.itemView.context, one_case::class.java)
                intent.putExtra("id",case.id)
                holder.itemView.context.startActivity(intent)

            }
        })
    }

    override fun getItemCount(): Int = cases.size
}