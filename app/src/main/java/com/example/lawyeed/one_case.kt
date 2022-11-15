package com.example.lawyeed

import Beans.OpenHelper
import Beans.service.API
import Beans.service.CaseBody
import Beans.service.`class`.*
import Helpers.Circle
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class one_case : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        var consultGlobal = OneCaseReponse(0,"","null","",PersonResponse(0,"","","","","","","",0,0,0),PersonResponse(0,"","","","","","","",0,0,0))
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_one_case)
        supportActionBar?.hide();

        var db: OpenHelper = OpenHelper(applicationContext)

        Picasso.get()
            .load(db.getUserImage())
            .resize(42, 42)
            .centerCrop()
            .transform(Circle())
            .into(findViewById<ShapeableImageView>(R.id.person_image))
        println(db.getUserImage())


        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://lawyeedapi.azurewebsites.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        }

        getRetrofit().create(API::class.java)
            .getOneCase("consults/"+ intent.extras?.getSerializable("id") ).enqueue(object : Callback<OneCaseReponse?> {
                override fun onResponse(
                    call: Call<OneCaseReponse?>,
                    response: Response<OneCaseReponse?>
                ) {

                    var item = response.body()!!
                    consultGlobal = response.body()!!
                    if(item.state == "CERRADO") {
                        var otrasOpciones = findViewById<LinearLayout>(R.id.otras_opciones)
                        var sendMessage = findViewById<LinearLayout>(R.id.send_messagge)
                        var otrasOpcionesText = findViewById<TextView>(R.id.otras_opciones_text)

                        otrasOpciones.visibility = View.GONE
                        sendMessage.visibility = View.GONE
                        otrasOpcionesText.visibility = View.GONE
                    }


                    var text_title = findViewById<TextView>(R.id.text_title)
                    var text_status = findViewById<TextView>(R.id.text_status)
                    var text_description = findViewById<TextView>(R.id.text_description)
                    var image_lawyer = findViewById<ImageView>(R.id.image_lawyer)
                    var lawyer_name = findViewById<TextView>(R.id.lawyer_name)
                    var lawyer_description = findViewById<TextView>(R.id.lawyer_description)

                    text_title.text = item.title
                    text_status.text = item.state
                    text_description.text = item.description
                    lawyer_name.text = item.lawyer.firstName + " " + item.lawyer.lastName
                    lawyer_description.text = item.lawyer.description

                    Picasso.get()
                        .load(item.lawyer.urlImage)
                        .resize(42, 42)
                        .centerCrop()
                        .transform(Circle())
                        .into(image_lawyer)

                    if(item.state  == "ABIERTO") {
                        text_status.setBackgroundResource(R.drawable.case_background_open)
                    } else {
                        text_status.setBackgroundResource(R.drawable.case_backfround_close)

                    }


                }

                override fun onFailure(call: Call<OneCaseReponse?>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        val listMessages = mutableListOf<Beans.Messages>()

        getRetrofit().create(API::class.java)
            .getMessageConsult("messages/"+intent.extras?.getSerializable("id") +"/consult").enqueue(object : Callback<List<MessageResponse>> {
                override fun onResponse(
                    call: Call<List<MessageResponse>>,
                    response: Response<List<MessageResponse>>
                ) {
                    var messages = response.body()!!
                    for(item in messages) {
                        listMessages.add(Beans.Messages(item.person.id,item.message,item.person.urlImage,item.person.type))
                    }

                    val recycler = findViewById<RecyclerView>(R.id.recycler_messages)
                    recycler.layoutManager= LinearLayoutManager(applicationContext)
                    recycler.adapter= com.example.lawyeed.messages.Adapter(listMessages, db.getUserId() )
                }

                override fun onFailure(call: Call<List<MessageResponse>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })



        val btnEnviar = findViewById<AppCompatButton>(R.id.input_enviar)
        val inputValue = findViewById<TextInputEditText>(R.id.input_text)
        btnEnviar.setOnClickListener() {
            if(inputValue.text.toString() != "") {
                getRetrofit().create(API::class.java)
                    .createMessage("messages",MessageBody(inputValue.text.toString(),intent.extras?.getSerializable("id").hashCode(), db.getUserId())).enqueue(object: Callback<MessageResponse> {
                        override fun onResponse(
                            call: Call<MessageResponse>,
                            response: Response<MessageResponse>
                        ) {
                            Toast.makeText(applicationContext,"El mensaje se ha enviado!", Toast.LENGTH_SHORT).show()
                            inputValue.text = Editable.Factory.getInstance().newEditable("")

                        }

                        override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })

                if(db.getUserType() == "client") {
                    getRetrofit().create(API::class.java)
                        .createNotification("notifications", NotificationBody(consultGlobal.title, "Haz recibido  mensajes del cliente, clic para ingresar al caso",consultGlobal.id, consultGlobal.lawyer.id)
                        ).enqueue(object: Callback<NotificationResponse> {
                            override fun onResponse(
                                call: Call<NotificationResponse>,
                                response: Response<NotificationResponse>
                            ) {

                            }

                            override fun onFailure(call: Call<NotificationResponse>, t: Throwable) {

                            }

                        })
                } else {
                    getRetrofit().create(API::class.java).createNotification("notifications",
                        NotificationBody(consultGlobal.title, "Haz recibido  mensajes del abogado, clic para ingresar al caso",consultGlobal.id, consultGlobal.client.id)
                    ).enqueue(object: Callback<NotificationResponse> {
                        override fun onResponse(
                            call: Call<NotificationResponse>,
                            response: Response<NotificationResponse>
                        ) {
                        }

                        override fun onFailure(call: Call<NotificationResponse>, t: Throwable) {
                        }

                    })
                }

            } else {
                Toast.makeText(applicationContext,"No puedes enviar el mensaje vacio!", Toast.LENGTH_SHORT).show()
            }
        }


        val btnCerrar = findViewById<LinearLayout>(R.id.btn_cerrar)

        btnCerrar.setOnClickListener() {
            getRetrofit().create(API::class.java)
                .updateCases("consults/"+intent.extras?.getSerializable("id"), CaseBody(consultGlobal.title,consultGlobal.description,"CERRADO",consultGlobal.client.id,consultGlobal.lawyer.id)).enqueue(object: Callback<OneCaseReponse> {
                    override fun onResponse(
                        call: Call<OneCaseReponse>,
                        response: Response<OneCaseReponse>
                    ) {
                        Toast.makeText(applicationContext, "Ha cerrado el caso exitosamente!", Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<OneCaseReponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })


        }
    }


}