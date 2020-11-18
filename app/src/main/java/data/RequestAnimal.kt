package data

import com.google.gson.annotations.SerializedName

data class RequestAnimal(
    @SerializedName("petName") val petName: String? = null,
    @SerializedName("breeds") val breeds: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("imgURL") val imgURL: String? = null
)

data class ResponseAnimal(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("petName") val petName: String? = null,
    @SerializedName("breeds") val breeds: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("imgURL") val password: Int? = null,
    @SerializedName("userID") val userId: Int? = null
)