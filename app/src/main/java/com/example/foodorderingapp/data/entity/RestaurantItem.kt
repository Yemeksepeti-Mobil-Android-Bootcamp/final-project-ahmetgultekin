package com.example.foodorderingapp.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestaurantItem(
    val name : String,
    val address : String,
    val category : String,
    val deliveryInfo : String,
    val deliveryTime : String,
    val imageUrl : String,
    val minOrder : String,
    val phone : String,
    val website : String,
):Parcelable