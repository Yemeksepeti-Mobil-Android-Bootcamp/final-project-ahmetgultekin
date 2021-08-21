package com.example.foodorderingapp.data.entity


import com.google.gson.annotations.SerializedName

data class OrderResponseItem(
    @SerializedName("accid")
    val accid: Accid,
    @SerializedName("email")
    val email: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("meal")
    val meal: FoodItem,
    @SerializedName("orderDate")
    val orderDate: String,
    @SerializedName("paymentType")
    val paymentType: List<String>,
    @SerializedName("price")
    val price: Double,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("restaurant")
    val restaurant: String,
    @SerializedName("__v")
    val v: Int
)