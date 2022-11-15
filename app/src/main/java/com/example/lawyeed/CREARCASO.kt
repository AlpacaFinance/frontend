package com.example.lawyeed

import Beans.OpenHelper
import Beans.service.API
import Beans.service.CASOPOST
import Beans.service.`class`.*
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CREARCASO: AppCompatActivity() {
    lateinit var service: API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contratar)
        supportActionBar?.hide();
        val button_crearCaso = findViewById<Button>(R.id.buttonCrearCaso)

        button_crearCaso.setOnClickListener{

            val ESTADO = "ABIERTO"
            var db = OpenHelper(applicationContext)
            var userId = db.getUserId()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://lawyeedapi.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val nombre_caso = findViewById<TextView>(R.id.nombreCaso)
            val descripcion_caso = findViewById<TextView>(R.id.descripcionCaso)

            val nombreCaso = nombre_caso.text.toString()
            val descripcionCaso = descripcion_caso.text.toString()


            val caso = CASOPOST(userId,descripcionCaso, 3,ESTADO,nombreCaso)

            service = retrofit.create(API::class.java)
            service.createCase(caso).enqueue(object: Callback<CASOPOST>{
                override fun onResponse(call: Call<CASOPOST>, response: Response<CASOPOST>) {
                    val addedCase= response.body()
                    Toast.makeText(applicationContext, "CASO CREADO", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<CASOPOST>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}