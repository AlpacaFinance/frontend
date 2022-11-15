package com.example.lawyeed

import Beans.OpenHelper
import Helpers.Circle
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class SearchSpecialty : AppCompatActivity(){

    lateinit var iniSearch: AppCompatButton
    lateinit var searchcases: EditText
    lateinit var searchspecialty: EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        var db: OpenHelper = OpenHelper(applicationContext)
        var personId = db.getUserId()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_specialty)
        supportActionBar?.hide();

        Picasso.get()
            .load(db.getUserImage())
            .resize(42, 42)
            .centerCrop()
            .transform(Circle())
            .into(findViewById<ShapeableImageView>(R.id.person_image))
        println(db.getUserImage())

        iniSearch = findViewById<AppCompatButton>(R.id.btnIniSearchSpecialty)
        searchspecialty = findViewById<EditText>(R.id.textInputEditTextSpecialty)
        searchcases = findViewById<EditText>(R.id.textInputEditTextCases)
        iniSearch.setOnClickListener {
            if ((searchcases.text.toString().toInt()>-1) and (searchspecialty.text.toString()!="")){
                val intent = Intent(this@SearchSpecialty, SearchResult::class.java)
                val type = "specialty"
                intent.putExtra("type", type)
                val cases = searchcases.text.toString()
                intent.putExtra("cases", cases)
                val specialty = searchcases.text.toString()
                intent.putExtra("specialty", specialty)
                startActivity(intent)
            }
        }

        findViewById<AppCompatButton>(R.id.btnregresarsearch).setOnClickListener() {
            startActivity(Intent(this, Search::class.java))
        }


    }


}