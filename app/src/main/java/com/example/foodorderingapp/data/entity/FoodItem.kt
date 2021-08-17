package com.example.foodorderingapp.data.entity

data class FoodItem(
    val name : String,
    val price : String,
    val imageUrl : String,
    val ingredients : ArrayList<String>
)