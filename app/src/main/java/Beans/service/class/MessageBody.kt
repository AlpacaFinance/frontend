package Beans.service.`class`

import com.google.gson.annotations.SerializedName

data class MessageBody(
    @SerializedName("messageToSend") var message: String,
    @SerializedName("consultId") var consult: Int,
    @SerializedName("personId") var person: Int,
)