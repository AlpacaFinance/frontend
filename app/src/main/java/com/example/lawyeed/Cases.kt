package com.example.lawyeed

import Beans.Cases
import Beans.OpenHelper
import Beans.service.API
import Beans.service.`class`.CasesResponse
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

class Cases : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        var db: OpenHelper = OpenHelper(applicationContext)
        var personId = db.getUserId()


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cases)
        supportActionBar?.hide();

        Picasso.get()
            .load(db.getUserImage())
            .resize(42, 42)
            .centerCrop()
            .transform(Circle())
            .into(findViewById<ShapeableImageView>(R.id.person_image))
        println(db.getUserImage())

        val listCases = mutableListOf<Beans.Cases>()
        val recycler = findViewById<RecyclerView>(R.id.recycler_cases)

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://lawyeedapi.azurewebsites.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        }

        getRetrofit().create(API::class.java)
            .getCases("persons/${personId}/consults").enqueue(object : Callback<List<CasesResponse>?> {
                override fun onResponse(
                    call: Call<List<CasesResponse>?>,
                    response: Response<List<CasesResponse>?>
                ) {
                    for(item in response.body()!!) {
                        listCases.add(Cases(item.id,item.title,item.description,item.status))
                    }

                    recycler.layoutManager= LinearLayoutManager(applicationContext)
                    recycler.adapter= com.example.lawyeed.cases.Adapter(listCases)
                }

                override fun onFailure(call: Call<List<CasesResponse>?>, t: Throwable) {
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
                    val intent = Intent(this, Notification::class.java)
                    startActivity(intent)
                }
                R.id.btnMenuBuscar -> {
                    val intent = Intent(this, Search::class.java)
                    startActivity(intent)
                }
                R.id.btnMenuCasos -> {
                    val intent = Intent(this, com.example.lawyeed.Cases::class.java)
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