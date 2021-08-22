package com.example.foodorderingapp.data.entity

import com.google.gson.annotations.SerializedName
import java.util.*

data class LoginResponse(
    @SerializedName("id") val id : String,
    @SerializedName("token") val token: String,
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("phone") val phone: Long,
    @SerializedName("address") var address : ArrayList<String>
)
