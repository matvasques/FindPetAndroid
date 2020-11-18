package data

import com.google.gson.annotations.SerializedName

data class Error(@SerializedName("error") val error: String? = null)