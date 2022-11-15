package com.example.lawyeed

import Beans.OpenHelper
import Beans.service.API
import Beans.service.`class`.PersonResponse
import Helpers.Circle
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
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

class SearchResult : AppCompatActivity() {

    lateinit var intentnames: String
    lateinit var intentcases: String
    lateinit var intentspecialty: String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        var db: OpenHelper = OpenHelper(applicationContext)
        var personId = db.getUserId()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        supportActionBar?.hide();

        Picasso.get()
            .load(db.getUserImage())
            .resize(42, 42)
            .centerCrop()
            .transform(Circle())
            .into(findViewById<ShapeableImageView>(R.id.person_image))
        println(db.getUserImage())

        findViewById<AppCompatButton>(R.id.btnregresarsearch).setOnClickListener() {
            startActivity(Intent(this, Search::class.java))
        }

        val type = intent.getStringExtra("type")
        if (type == "name"){
            intentnames = intent.getStringExtra("names").toString()
        }
        if (type == "specialty"){
            intentcases = intent.getStringExtra("cases").toString()
            intentspecialty = intent.getStringExtra("specialty").toString()
        }

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://lawyeedapi.azurewebsites.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }

        val listLawyers = mutableListOf<Beans.Lawyers>()

        getRetrofit().create(API::class.java)
            .getLawyers("personlawyers/").enqueue(object : Callback<List<PersonResponse>?> {
                override fun onResponse(
                    call: Call<List<PersonResponse>?>,
                    response: Response<List<PersonResponse>?>
                ) {
                    for(item in response.body()!!) {
                        if (type == "name"){
                            val tmpnames = item.firstName.toString() + " " + item.lastName.toString()
                            if(tmpnames.contains(intentnames.toString())) {
                                listLawyers.add(
                                    Beans.Lawyers(
                                        item.id,
                                        item.firstName,
                                        item.lastName,
                                        item.email,
                                        "",
                                        item.description,
                                        item.urlImage,
                                        item.type,
                                        item.specialty,
                                        item.wonCases,
                                        item.totalCases,
                                        item.lostCases
                                    )
                                )
                            }
                        }
                        if (type == "specialty"){
                            val tmpcases = item.wonCases
                            if((intentcases.toInt() >= tmpcases) and (item.specialty.contains(intentspecialty))) {
                                listLawyers.add(
                                    Beans.Lawyers(
                                        item.id,
                                        item.firstName,
                                        item.lastName,
                                        item.email,
                                        "",
                                        item.description,
                                        item.urlImage,
                                        item.type,
                                        item.specialty,
                                        item.wonCases,
                                        item.totalCases,
                                        item.lostCases
                                    )
                                )
                            }
                        }
                        if (type == "all"){
                            listLawyers.add(
                                Beans.Lawyers(
                                    item.id,
                                    item.firstName,
                                    item.lastName,
                                    item.email,
                                    "",
                                    item.description,
                                    item.urlImage,
                                    item.type,
                                    item.specialty,
                                    item.wonCases,
                                    item.totalCases,
                                    item.lostCases
                                )
                            )
                        }

                    }
                    var recycler = findViewById<RecyclerView>(R.id.recycler_searchlawyers)
                    val verticalLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                    recycler.layoutManager = verticalLayoutManager
                    recycler.adapter= com.example.lawyeed.search.Adapter(listLawyers)
                }

                override fun onFailure(call: Call<List<PersonResponse>?>, t: Throwable) {
                    t?.printStackTrace()
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
    }

}