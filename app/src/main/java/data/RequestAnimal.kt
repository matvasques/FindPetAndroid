package data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RequestAnimal(
    @SerializedName("petName") val petName: String? = null,
    @SerializedName("breeds") val breeds: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("imgURL") val imgURL: String? = null
)

data class ResponseAnimal(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("petName") var petName: String? = null,
    @SerializedName("breeds") var breeds: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("imgURL") var imgUrl: String? = null,
    @SerializedName("userID") var userId: Int? = null
) : Serializable


fun ResponseAnimal.toRequestAnimal() =
    RequestAnimal(this.petName, this.breeds, this.description, this.imgUrl)