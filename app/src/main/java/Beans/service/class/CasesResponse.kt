package Beans.service.`class`

import com.google.gson.annotations.SerializedName

class CasesResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("state") var status: String
)

{
}