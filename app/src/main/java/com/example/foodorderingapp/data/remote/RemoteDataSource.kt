package com.example.foodorderingapp.data.remote

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodorderingapp.data.entity.AddressRequest
import com.example.foodorderingapp.data.entity.LoginRequest
import com.example.foodorderingapp.data.entity.Order
import com.example.foodorderingapp.data.entity.RegisterRequest
import com.example.foodorderingapp.utils.BaseDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: RestaurantApiService) : BaseDataSource() {
    private val isSuccesful = MutableLiveData<Boolean>()
    suspend fun getAllRestaurants(page:Int) = getResult { apiService.getAllRestaurants(page) }

    suspend fun getRestaurantsByCategory(categoryName : String) = getResult { apiService.getRestaurantsByCategory(categoryName) }

    suspend fun login(loginRequest: LoginRequest) = getResult { apiService.login(loginRequest) }

    suspend fun register(registerRequest: RegisterRequest) = getResult { apiService.register(registerRequest) }

    suspend fun getOrders(userId: String) = getResult { apiService.getOrders(userId) }

    suspend fun updateAddress(addressRequest: AddressRequest) = getResult { apiService.updateAddress(addressRequest) }

    suspend fun order(order: Order){
        apiService.order(order)
    }
}