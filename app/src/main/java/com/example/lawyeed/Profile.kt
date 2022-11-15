package com.example.lawyeed

import Beans.OpenHelper
import Beans.service.API
import Beans.service.`class`.Client
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar!!.hide()

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://lawyeedapi.azurewebsites.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        }
        val btnEditarPerfil: Button = findViewById(R.id.btnEditarPerfil)
        val btnComprarSubscripcion: Button = findViewById(R.id.btnComprarSubscripcion)

        var db: OpenHelper = OpenHelper(applicationContext)

        Picasso.get()
            .load(db.getUserImage())
            .into(findViewById<ShapeableImageView>(R.id.myphotomini))
        Picasso.get()
            .load(db.getUserImage())
            .into(findViewById<ShapeableImageView>(R.id.myphoto))
        println(db.getUserImage())

        val myId = db.getUserId()

        getRetrofit().create(API::class.java)
            .getUserData("person/${myId}")
            .enqueue(object : Callback<Client?> {
                override fun onResponse(call: Call<Client?>, response: Response<Client?>) {

                    val item = response.body()!!

                    val textNombre: TextView = findViewById(R.id.textNombre)
                    val textDescripcion: TextView = findViewById(R.id.textDescripcion)
                    val textEmail: TextView = findViewById(R.id.textEmail)

                    textNombre.text = item.fisrtName + ' ' + item.lastName
                    textDescripcion.text = item.description
                    textEmail.text = item.email
                }

                override fun onFailure(call: Call<Client?>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        btnEditarPerfil.setOnClickListener() {
            val intent = Intent(this, ProfileEdit::class.java)
            startActivity(intent)
        }

        btnComprarSubscripcion.setOnClickListener() {
            val intent = Intent(this, Subscription::class.java)
            startActivity(intent)
        }

        val btnBotAppBar: BottomNavigationView = findViewById(R.id.bottom_navigation)
        btnBotAppBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnMenuInicio -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.btnMenuNotificaciones -> {
                    val intent = Intent(this, Notification::class.java)
                    startActivity(intent)
                }
                R.id.btnMenuBuscar -> {
                    val intent = Intent(this, Search::class.java)
                    startActivity(intent)
                }
                R.id.btnMenuCasos -> {
                    val intent = Intent(this, Cases::class.java)
                    startActivity(intent)
                }
            }
            true
        }

    }


}