package data

import com.google.gson.annotations.SerializedName

data class CommonResponse(
    @SerializedName("msg") val message: String? = null,
    @SerializedName("error") val errorMessage: String? = null
)