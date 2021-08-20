package com.example.foodorderingapp.data.remote

import com.example.foodorderingapp.data.entity.LoginRequest
import com.example.foodorderingapp.data.entity.RegisterRequest
import com.example.foodorderingapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: RestaurantApiService) : BaseDataSource() {

    suspend fun getAllRestaurants() = getResult { apiService.getAllRestaurants() }

    suspend fun getRestaurantsByCategory(categoryName : String) = getResult { apiService.getRestaurantsByCategory(categoryName) }

    suspend fun login(loginRequest: LoginRequest) = getResult { apiService.login(loginRequest) }

    suspend fun register(registerRequest: RegisterRequest) = getResult { apiService.register(registerRequest) }
}