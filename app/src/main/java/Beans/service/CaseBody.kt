package Beans.service

import com.google.gson.annotations.SerializedName

class CaseBody (
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("state") var state: String,
    @SerializedName("clientId") var clientId: Int,
    @SerializedName("lawyerId") var lawyerId: Int

)