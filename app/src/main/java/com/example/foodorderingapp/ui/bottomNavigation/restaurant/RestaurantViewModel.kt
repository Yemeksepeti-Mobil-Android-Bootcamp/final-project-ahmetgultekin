package com.example.foodorderingapp.ui.bottomNavigation.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodorderingapp.data.ApiRepository
import com.example.foodorderingapp.data.entity.RestaurantByCategoryResponse
import com.example.foodorderingapp.data.entity.RestaurantResponse
import com.example.foodorderingapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(val apiRepository: ApiRepository) : ViewModel() {

    fun getAllRestaurants(page:Int): LiveData<Resource<RestaurantResponse>>{
        return apiRepository.getAllRestaurants(page)
    }
    fun getRestaurantByCategory(categoryName : String) : LiveData<Resource<RestaurantByCategoryResponse>>{
        return apiRepository.getRestaurantByCategory(categoryName)
    }


}