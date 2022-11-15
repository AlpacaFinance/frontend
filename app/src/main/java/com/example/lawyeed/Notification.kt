package com.example.lawyeed

import Beans.Notification
import Beans.OpenHelper
import Beans.service.API
import Beans.service.`class`.NotificationResponse
import Helpers.Circle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.notification.Adapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Notification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var db: OpenHelper = OpenHelper(applicationContext)
        var personId = db.getUserId()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        supportActionBar?.hide();

        Picasso.get()
            .load(db.getUserImage())
            .resize(42, 42)
            .centerCrop()
            .transform(Circle())
            .into(findViewById<ShapeableImageView>(R.id.person_image))
        println(db.getUserImage())

        val listNotification = mutableListOf<Notification>()
        val recycler = findViewById<RecyclerView>(R.id.recycler_notifications)

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://lawyeedapi.azurewebsites.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        }

        getRetrofit().create(API::class.java)
            .getNotifications("notifications/persons/${personId}").enqueue(object : Callback<List<NotificationResponse>?> {
                override fun onResponse(
                    call: Call<List<NotificationResponse>?>,
                    response: Response<List<NotificationResponse>?>
                ) {
                    for(item in response.body()!!) {
                        listNotification.add(Notification(item.id,item.title,item.description,item.consult.id))
                    }

                    recycler.layoutManager= LinearLayoutManager(applicationContext)
                    recycler.adapter= Adapter(listNotification)
                }

                override fun onFailure(call: Call<List<NotificationResponse>?>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        //Ingresar a mi perfil
        val btnIngresarPerfil: ShapeableImageView = findViewById(R.id.person_image)
        btnIngresarPerfil.setOnClickListener() {
            val intent = Intent(this, Profile::class.java)
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
                    val intent = Intent(this, com.example.lawyeed.Notification::class.java)
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