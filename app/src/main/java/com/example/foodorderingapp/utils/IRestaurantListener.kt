package com.example.foodorderingapp.utils

import com.example.foodorderingapp.data.entity.RestaurantItem
import com.example.foodorderingapp.data.entity.RestaurantResponseItem

interface IRestaurantListener {
    fun onClick(restaurantItem: RestaurantResponseItem)
}