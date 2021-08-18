package com.example.foodorderingapp.utils

import com.example.foodorderingapp.data.entity.FoodItem

interface IFoodListener {
    fun onClick(foodItem: FoodItem)
}