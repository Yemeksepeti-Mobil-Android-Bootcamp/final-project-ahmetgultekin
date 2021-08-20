package com.example.foodorderingapp.data.remote

import com.example.foodorderingapp.data.entity.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RestaurantApiService {

    @GET("restaurants")
    suspend fun getAllRestaurants() : Response<RestaurantResponse>

    @GET("restaurants/{categoryName}")
    suspend fun getRestaurantsByCategory(@Path ("categoryName") categoryName : String) : Response<RestaurantByCategoryResponse>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest) : Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest) : Response<LoginResponse>

    //POST api/order -> accountId,meal,resturantName, quantity,orderDate
    //GET api/order/(id)
}