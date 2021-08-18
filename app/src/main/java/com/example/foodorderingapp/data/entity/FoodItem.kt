package com.example.foodorderingapp.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodItem(
    val name : String,
    val price : String,
    val imageUrl : String,
    val ingredients : ArrayList<String>
):Parcelable