package Beans.service.`class`

import com.google.gson.annotations.SerializedName

class PersonResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("fisrtName") var firstName: String,
    @SerializedName("lastName") var lastName: String,
    @SerializedName("email") var email: String,
    @SerializedName("description") var description: String,
    @SerializedName("urlImage") var urlImage: String,
    @SerializedName("type") var type: String,
    @SerializedName("specialty") var specialty: String,
    @SerializedName("wonCases") var wonCases: Int,
    @SerializedName("totalCases") var totalCases: Int,
    @SerializedName("lostCases") var lostCases: Int
)

