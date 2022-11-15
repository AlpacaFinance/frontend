package com.example.lawyeed.lawyers_home

import Beans.Lawyers
import Helpers.Circle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.R
import com.squareup.picasso.Picasso

class LawyersHomeListViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val name = view.findViewById<TextView>(R.id.text_name)
    val image = view.findViewById<ImageView>(R.id.image_name)

    fun render(lawyers:Lawyers){
        name.text = lawyers.fisrtName.toString()
        Picasso.get()
            .load(lawyers.urlImage.toString())
            .resize(42, 42)
            .centerCrop()
            .transform(Circle())
            .into(image)


    }

}