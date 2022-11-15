package Beans.service.`class`

import com.google.gson.annotations.SerializedName

class OneCaseReponse (
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("state") var state: String,
    @SerializedName("client") var client: PersonResponse,
    @SerializedName("lawyer") var lawyer: PersonResponse,

)