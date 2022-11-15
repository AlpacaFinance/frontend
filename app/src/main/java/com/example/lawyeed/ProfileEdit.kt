package com.example.lawyeed

import Beans.OpenHelper
import Beans.service.API
import Beans.service.`class`.CasoItem
import Beans.service.`class`.Client
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileEdit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)
        supportActionBar!!.hide()

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://lawyeedapi.azurewebsites.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        }

        val btnActualizarPerfil: Button = findViewById(R.id.btnActualizarPerfil)

        var db: OpenHelper = OpenHelper(applicationContext)

        Picasso.get()
            .load(db.getUserImage())
            .into(findViewById<ShapeableImageView>(R.id.myphotomini))
        Picasso.get()
            .load(db.getUserImage())
            .into(findViewById<ShapeableImageView>(R.id.myphoto))
        println(db.getUserImage())

        val myId = db.getUserId()

        var p_password:String = ""
        var p_type:String = ""
        var p_urlImage:String = ""

        getRetrofit().create(API::class.java)
            .getUserData("person/${myId}")
            .enqueue(object : Callback<Client?> {
                override fun onResponse(call: Call<Client?>, response: Response<Client?>) {

                    val item = response.body()!!

                    val editNombre: EditText = findViewById(R.id.editNombre)
                    val editApellido: EditText = findViewById(R.id.editApellido)
                    val editDescripcion: EditText = findViewById(R.id.editDescripcion)
                    val editEmail: EditText = findViewById(R.id.editEmail)

                    editNombre.setText(item.fisrtName)
                    editApellido.setText(item.lastName)
                    editDescripcion.setText(item.description)
                    editEmail.setText(item.email)

                    p_password = item.password
                    p_type = item.type
                    p_urlImage = item.urlImage

                }

                override fun onFailure(call: Call<Client?>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        btnActualizarPerfil.setOnClickListener() {

            val editNombre: EditText = findViewById(R.id.editNombre)
            val editApellido: EditText = findViewById(R.id.editApellido)
            val editDescripcion: EditText = findViewById(R.id.editDescripcion)
            val editEmail: EditText = findViewById(R.id.editEmail)

            val usuarioObj = Client(
                editDescripcion.text.toString(),
                editEmail.text.toString(),
                editNombre.text.toString(),
                myId,
                editApellido.text.toString(),
                p_password,
                p_type,
                p_urlImage
            )

            getRetrofit().create(API::class.java)
                .updateUserData("person/?id=${myId}", usuarioObj)
                .enqueue(object : Callback<Client?> {
                    override fun onResponse(call: Call<Client?>, response: Response<Client?>) {
                        /* -- */
                    }

                    override fun onFailure(call: Call<Client?>, t: Throwable) {
                        /* -- */
                    }

                })

            val intent = Intent(this, ProfileUpdated::class.java)
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