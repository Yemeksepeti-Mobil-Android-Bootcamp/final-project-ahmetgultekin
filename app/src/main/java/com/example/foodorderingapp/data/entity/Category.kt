package com.example.foodorderingapp.data.entity


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    @SerializedName("categoryName")
    val categoryName: String
):Parcelable