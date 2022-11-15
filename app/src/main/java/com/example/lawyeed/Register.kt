package com.example.lawyeed

import Beans.OpenHelper
import Beans.service.API
import Beans.service.`class`.Client
import Beans.service.`class`.RegisterClient
import Beans.service.`class`.RegisterLawyer
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.properties.Delegates

class Register : AppCompatActivity() {

    lateinit var fname: TextInputEditText
    lateinit var lname: TextInputEditText
    lateinit var mail: TextInputEditText
    lateinit var pass: TextInputEditText
    lateinit var registrar: AppCompatButton
    lateinit var chcliente: CheckBox
    lateinit var chabogado: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://lawyeedapi.azurewebsites.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar!!.hide()

        chcliente=findViewById(R.id.rcheckbox_cliente)
        chabogado=findViewById(R.id.rcheckbox_abogado)

        registrar=findViewById(R.id.buttonRegister)

        registrar.setOnClickListener(){

            fname=findViewById(R.id.rFirstName)
            lname=findViewById(R.id.rLastName)
            mail=findViewById(R.id.rEmail)
            pass=findViewById(R.id.rPassword)

            var cliente = false
            var abogado = false

            if (chcliente.isChecked){
                cliente=true
            }
            if (chabogado.isChecked){
                abogado=true
            }

            if ((fname.text.toString() == "") or (lname.text.toString() == "")){
                Toast.makeText(applicationContext,"Por favor ingresa tu nombres",Toast.LENGTH_SHORT).show()
            }
            else if (mail.text.toString() == ""){
                Toast.makeText(applicationContext,"Por favor ingresa tu correo",Toast.LENGTH_SHORT).show()
            }
            else if (pass.text.toString() == ""){
                Toast.makeText(applicationContext,"Por favor ingresa tu contrasena",Toast.LENGTH_SHORT).show()
            }
            else if (!cliente and !abogado){
                Toast.makeText(applicationContext,"Por favor elige un tipo de cuenta",Toast.LENGTH_SHORT).show()
            }
            else if (cliente and abogado){
                Toast.makeText(applicationContext,"Por favor elige solo un tipo de cuenta",Toast.LENGTH_SHORT).show()
            }
            else if (cliente or abogado){
                if (cliente){
                    val personObj = RegisterClient(
                        fname.text.toString(),
                        lname.text.toString(),
                        mail.text.toString(),
                        pass.text.toString(),
                        "string",
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Default_pfp.svg/1200px-Default_pfp.svg.png",
                        "client"
                    )

                    getRetrofit().create(API::class.java)
                        .createPerson("person/register",personObj)
                        .enqueue(object : Callback<RegisterClient?> {
                            override fun onResponse(call: Call<RegisterClient?>, response: Response<RegisterClient?>) {
                                Toast.makeText(applicationContext,"Exitosamente creastes tu cuenta",Toast.LENGTH_SHORT).show()
                                startActivity(Intent(applicationContext, Login::class.java))
                            }

                            override fun onFailure(call: Call<RegisterClient?>, t: Throwable) {
                                Toast.makeText(applicationContext,"Error, por favor intente mas tarde registrarte",Toast.LENGTH_SHORT).show()
                            }
                        })

                }
                if (abogado){
                    val lawyerObj = RegisterLawyer(
                        fname.text.toString(),
                        lname.text.toString(),
                        mail.text.toString(),
                        pass.text.toString(),
                        "string",
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Default_pfp.svg/1200px-Default_pfp.svg.png",
                        "lawyer",
                        "string",
                        0,
                        0,
                        0
                    )

                    getRetrofit().create(API::class.java)
                        .createLaywer("personlawyers/register",lawyerObj)
                        .enqueue(object : Callback<RegisterLawyer?> {
                            override fun onResponse(call: Call<RegisterLawyer?>, response: Response<RegisterLawyer?>) {
                                Toast.makeText(applicationContext,"Exitosamente creastes tu cuenta",Toast.LENGTH_SHORT).show()
                                startActivity(Intent(applicationContext, Login::class.java))
                            }

                            override fun onFailure(call: Call<RegisterLawyer?>, t: Throwable) {
                                Toast.makeText(applicationContext,"Error, por favor intente mas tarde registrarte",Toast.LENGTH_SHORT).show()
                            }
                        })
                }
            }

        }

    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.rcheckbox_cliente -> {

                }
                R.id.rcheckbox_abogado -> {

                }
            }
        }
    }

}