package data

import com.google.gson.annotations.SerializedName

data class RequestUser(
    @SerializedName("name") val name: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("phoneNumber") val phoneNumber: String? = null,
    @SerializedName("password") val password: String? = null,
    @SerializedName("passwordConfirmation") val passwordConfirmation: String? = null,
    @SerializedName("publicPlace") val publicPlace: String? = null,
    @SerializedName("houseNumber") val houseNumber: Int? = null,
    @SerializedName("complement") val complement: String? = null,
    @SerializedName("postalCode") val postalCode: String? = null,
    @SerializedName("neighborhood") val neighborhood: String? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("uf") val uf: String? = null,
    @SerializedName("country") val country: String? = null
)

data class ResponseUser(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("phoneNumber") val phoneNumber: String? = null,
    @SerializedName("password") val password: String? = null,
    @SerializedName("publicPlace") val publicPlace: String? = null,
    @SerializedName("houseNumber") val houseNumber: Int? = null,
    @SerializedName("complement") val complement: String? = null,
    @SerializedName("postalCode") val postalCode: String? = null,
    @SerializedName("neighborhood") val neighborhood: String? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("uf") val uf: String? = null,
    @SerializedName("country") val country: String? = null
)

data class DeleteUserRequest(@SerializedName("id") val id: String? = null)


