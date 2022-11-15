package com.example.lawyeed.messages

import Beans.Messages
import Helpers.Circle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.R
import com.squareup.picasso.Picasso


class MessagesListViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val text = view.findViewById<TextView>(R.id.message)
    val image = view.findViewById<ImageView>(R.id.image)
    val linearLayout  = view.findViewById<LinearLayout>(R.id.ml)

    fun render(message:Messages,personId:Int){
        text.text = message.text

        if(personId == message.id) {
            setMargins(linearLayout, 200,0,0,30)
        } else {
            setMargins(linearLayout, 0,0,0,30)

        }




        Picasso.get()
            .load(message.image)
            .resize(42, 42)
            .centerCrop()
            .transform(Circle())
            .into(image)
    }
    private fun setMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        if (view.layoutParams is MarginLayoutParams) {
            val p = view.layoutParams as MarginLayoutParams
            p.setMargins(left, top, right, bottom)
            view.requestLayout()
        }
    }
}