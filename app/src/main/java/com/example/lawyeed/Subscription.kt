package com.example.lawyeed

import Beans.Subscription
import Beans.OpenHelper
import Beans.service.API
import Beans.service.`class`.SubscriptionResponse
import Helpers.Circle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Subscription : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        var db: OpenHelper = OpenHelper(applicationContext)
        var personId = db.getUserId()


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscription)
        supportActionBar?.hide();

        Picasso.get()
            .load(db.getUserImage())
            .resize(42, 42)
            .centerCrop()
            .transform(Circle())
            .into(findViewById<ShapeableImageView>(R.id.person_image))
        println(db.getUserImage())

        val listSubscription = mutableListOf<Beans.Subscription>()
        val recycler = findViewById<RecyclerView>(R.id.recycler_subscription)

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://lawyeedapi.azurewebsites.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        }

        getRetrofit().create(API::class.java)
            .getSubscriptions("plan").enqueue(object : Callback<List<SubscriptionResponse>?> {
                override fun onResponse(
                    call: Call<List<SubscriptionResponse>?>,
                    response: Response<List<SubscriptionResponse>?>
                ) {
                    for(item in response.body()!!) {
                        listSubscription.add(Subscription(item.id,item.name,item.description, item.price))
                    }

                    recycler.layoutManager= LinearLayoutManager(applicationContext)
                    recycler.adapter= com.example.lawyeed.subscription.Adapter(listSubscription)
                }

                override fun onFailure(call: Call<List<SubscriptionResponse>?>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        //Ingresar a mi perfil
        val btnIngresarPerfil: ShapeableImageView = findViewById(R.id.person_image)
        btnIngresarPerfil.setOnClickListener() {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        //Menu de abajo
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








        /*
        listCases.add(Beans.Cases(1,"1Maltrato Domestico", "simply dummy text of the printing and typesetting industry. Lorem Ipsum ha" +
                "s been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galle dsfsd sfsds", "ABIERTO"))
        listCases.add(Beans.Cases(1,"Maltrato Domestico", "simply dummy text of the printing and typesetting industry. Lorem Ipsum ha" +
                "s been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galle dsfsd sfsds", "CERRADO"))
        listCases.add(Beans.Cases(1,"Maltrato Domestico", "simply dummy text of the printing and typesetting industry. Lorem Ipsum ha" +
                "s been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galle dsfsd sfsds", "ABIERTO"))
        listCases.add(Beans.Cases(1,"Maltrato Domestico", "simply dummy text of the printing and typesetting industry. Lorem Ipsum ha" +
                "s been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galle dsfsd sfsds", "CERRADO"))
        listCases.add(Beans.Cases(1,"Maltrato Domestico", "simply dummy text of the printing and typesetting industry. Lorem Ipsum ha" +
                "s been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galle dsfsd sfsds", "ABIERTO"))
        listCases.add(Beans.Cases(1,"Maltrato Domestico", "simply dummy text of the printing and typesetting industry. Lorem Ipsum ha" +
                "s been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galle dsfsd sfsds", "CERRADO"))
        listCases.add(Beans.Cases(1,"Maltrato Domestico", "simply dummy text of the printing and typesetting industry. Lorem Ipsum ha" +
                "s been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galle dsfsd sfsds", "ABIERTO"))
        listCases.add(Beans.Cases(1,"Maltrato Domestico", "simply dummy text of the printing and typesetting industry. Lorem Ipsum ha" +
                "s been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galle dsfsd sfsds", "ABIERTO"))
        listCases.add(Beans.Cases(1,"Maltrato Domestico", "simply dummy text of the printing and typesetting industry. Lorem Ipsum ha" +
                "s been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galle dsfsd sfsds", "CERRADO"))
        listCases.add(Beans.Cases(1,"Maltrato Domestico", "simply dummy text of the printing and typesetting industry. Lorem Ipsum ha" +
                "s been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galle dsfsd sfsds", "ABIERTO"))
        listCases.add(Beans.Cases(1,"Maltrato Domestico", "simply dummy text of the printing and typesetting industry. Lorem Ipsum ha" +
                "s been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galle dsfsd sfsds", "CERRADO"))
        listCases.add(Beans.Cases(1,"Maltrato Domestico", "simply dummy text of the printing and typesetting industry. Lorem Ipsum ha" +
                "s been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galle dsfsd sfsds", "ABIERTO"))
        listCases.add(Beans.Cases(1,"Maltrato Domestico", "simply dummy text of the printing and typesetting industry. Lorem Ipsum ha" +
                "s been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galle dsfsd sfsds", "CERRADO"))

         */

    }
}