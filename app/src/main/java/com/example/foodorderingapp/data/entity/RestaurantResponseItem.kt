package com.example.foodorderingapp.data.entity


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestaurantResponseItem(
    @SerializedName("address")
    val address: String,
    @SerializedName("category")
    val category: Category,
    @SerializedName("deliveryInfo")
    val deliveryInfo: String,
    @SerializedName("deliveryTime")
    val deliveryTime: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("menu")
    val menu: List<FoodItem>,
    @SerializedName("minDeliveryFee")
    val minDeliveryFee: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: Long,
    @SerializedName("website")
    val website: String
):Parcelable