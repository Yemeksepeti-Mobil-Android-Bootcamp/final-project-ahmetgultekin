package com.example.foodorderingapp.ui.bottomNavigation.restaurantDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodorderingapp.data.entity.RestaurantItem

class RestaurantDetailViewModel : ViewModel() {
    private val restaurant = MutableLiveData<RestaurantItem>()

    fun setClickedRestaurant(restaurantItem: RestaurantItem){
        restaurant.value = restaurantItem
    }
    fun getClickedRestaurant(): LiveData<RestaurantItem> {
        return restaurant
    }
}