package com.example.foodorderingapp.ui.bottomNavigation.account.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.foodorderingapp.data.ApiRepository
import com.example.foodorderingapp.data.entity.LoginResponse
import com.example.foodorderingapp.data.entity.OrderResponse
import com.example.foodorderingapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel(){

    fun getOrders(userId: String) : LiveData<Resource<OrderResponse>>{
        return apiRepository.getOrders(userId)
    }
    fun getUserInfo() : LoginResponse?{
        return apiRepository.getUserInfo()
    }
}