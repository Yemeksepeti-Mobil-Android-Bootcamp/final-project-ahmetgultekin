package com.example.foodorderingapp.ui.bottomNavigation.restaurantDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodorderingapp.data.entity.RestaurantItem
import com.example.foodorderingapp.data.entity.RestaurantResponseItem

class RestaurantDetailViewModel : ViewModel() {
    private val restaurant = MutableLiveData<RestaurantResponseItem>()

    fun setClickedRestaurant(restaurantItem: RestaurantResponseItem){
        restaurant.value = restaurantItem
    }
    fun getClickedRestaurant(): LiveData<RestaurantResponseItem> {
        return restaurant
    }
}