package com.example.foodorderingapp.utils

import com.example.foodorderingapp.data.entity.RestaurantItem

interface IRestaurantListener {
    fun onClick(restaurantItem: RestaurantItem)
}