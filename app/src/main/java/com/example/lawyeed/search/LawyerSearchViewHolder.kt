package com.example.lawyeed.search

import Beans.Lawyers
import Helpers.Circle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.R
import com.squareup.picasso.Picasso

class LawyerSearchViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val names = view.findViewById<TextView>(R.id.rtext_names)
    val description = view.findViewById<TextView>(R.id.rtext_description)
    val image = view.findViewById<ImageView>(R.id.rimage_name)

    fun render(search: Lawyers){
        names.text = search.fisrtName.toString() + " " + search.lastName.toString()
        description.text = search.description.toString()
        Picasso.get()
            .load(search.urlImage.toString())
            .resize(42, 42)
            .centerCrop()
            .transform(Circle())
            .into(image)
    }
}