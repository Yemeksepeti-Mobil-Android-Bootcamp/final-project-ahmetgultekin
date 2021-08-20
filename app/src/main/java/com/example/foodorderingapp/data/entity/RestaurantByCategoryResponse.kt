package com.example.foodorderingapp.data.entity


import com.google.gson.annotations.SerializedName

data class RestaurantByCategoryResponse(
    @SerializedName("queryCategory")
    val restaurants: List<RestaurantResponseItem>
)