package Beans.service.`class`

import com.google.gson.annotations.SerializedName

data class NotificationBody(
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("consultId") var consult: Int,
    @SerializedName("personId") var person: Int,
)