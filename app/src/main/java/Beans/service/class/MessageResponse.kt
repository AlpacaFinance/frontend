package Beans.service.`class`

import com.google.gson.annotations.SerializedName

class MessageResponse (
    @SerializedName("id") var id: Int,
    @SerializedName("messageToSend") var message: String,
    @SerializedName("consult") var consult: OneCaseReponse,
    @SerializedName("person") var person: PersonResponse,
)