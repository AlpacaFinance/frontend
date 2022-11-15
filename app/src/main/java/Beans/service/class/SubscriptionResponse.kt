package Beans.service.`class`

import com.google.gson.annotations.SerializedName

class SubscriptionResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String,
    @SerializedName("price") var price: Int
)

{
}