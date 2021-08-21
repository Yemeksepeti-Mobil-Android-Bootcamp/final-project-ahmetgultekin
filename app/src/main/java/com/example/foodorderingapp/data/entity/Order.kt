package com.example.foodorderingapp.data.entity


import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("accid")
    val accid: Accid,
    @SerializedName("email")
    val email: String,
    @SerializedName("meal")
    val meal : FoodItem,
    @SerializedName("orderDate")
    val orderDate: String,
    @SerializedName("paymentType")
    val paymentType: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("restaurant")
    val restaurant: String
)