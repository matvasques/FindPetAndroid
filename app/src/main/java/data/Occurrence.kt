package data

import com.google.gson.annotations.SerializedName

data class RequestOccurrence(@SerializedName("description") val description: String?)

data class ResponseOccurrence(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("startDate") val startDate: String? = null,
    @SerializedName("endDate") val endDate: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("animalID") val animalID: Int? = null
)
