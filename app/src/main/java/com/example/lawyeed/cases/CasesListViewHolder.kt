package com.example.lawyeed.cases

import Beans.Cases
import Beans.Notification
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.R

class CasesListViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val title = view.findViewById<TextView>(R.id.text_title)
    val description = view.findViewById<TextView>(R.id.text_description)
    val status = view.findViewById<TextView>(R.id.text_status)

    fun render(cases:Cases){
        title.text = cases.title.toString()
        description.text = cases.description.toString();
        status.text = cases.status.toString();

        if(cases.status == "ABIERTO") {
            status.setBackgroundResource(R.drawable.case_background_open)
        } else {
            status.setBackgroundResource(R.drawable.case_backfround_close)

        }


    }

}