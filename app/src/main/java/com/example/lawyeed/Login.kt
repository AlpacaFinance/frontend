package com.example.lawyeed

import Beans.OpenHelper
import Beans.service.API
import Beans.service.`class`.PersonResponse
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login : AppCompatActivity() {

    private lateinit var buttonLogin: Button
    private lateinit var buttonRegister: AppCompatTextView


    override fun onCreate(savedInstanceState: Bundle?) {
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://lawyeedapi.azurewebsites.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        }
        var db: OpenHelper = OpenHelper(applicationContext)
        if(db.getUserId() != -1){
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar!!.hide()
        initObjects()

        buttonLogin = findViewById(R.id.buttonLogin)
        buttonRegister = findViewById(R.id.textViewLinkRegister)

        buttonRegister.setOnClickListener() {
            startActivity(Intent(applicationContext, Register::class.java))
        }

        buttonLogin.setOnClickListener() {
            var email = findViewById<TextInputEditText>(R.id.email).text
            var password = findViewById<TextInputEditText>(R.id.password).text

            getRetrofit().create(API::class.java).login("person/login?email=${email}&password=${password}").enqueue(
                object : Callback<PersonResponse?> {
                    override fun onResponse(
                        call: Call<PersonResponse?>,
                        response: Response<PersonResponse?>
                    ) {
                        if(response.isSuccessful) {
                            var user = response.body()
                            if (user != null) {
                                println("true")
                                Toast.makeText(applicationContext,"Bienvenido ${user.firstName} ${user.lastName} ",Toast.LENGTH_SHORT).show()
                                db.addUser(user.id, user.firstName, user.lastName, user.email, user.description, user.urlImage, user.type, "--", 0, 0, 0)
                                println(db.getUserId())

                                startActivity(Intent(applicationContext, MainActivity::class.java))
                                return
                            }

                        }
                        Toast.makeText(applicationContext,"Datos Incorrectos",Toast.LENGTH_SHORT).show()


                    }

                    override fun onFailure(call: Call<PersonResponse?>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

                    }
                }
            )
        }
    }



    private fun initObjects() {

    }




}