package com.example.foodorderingapp.data.entity

import com.google.gson.annotations.SerializedName

data class AddressRequest(
    @SerializedName("id") val id:String,
    @SerializedName("address") val address: String
)
