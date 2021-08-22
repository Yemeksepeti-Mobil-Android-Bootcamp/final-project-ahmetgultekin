package com.example.foodorderingapp.data.remote

import com.example.foodorderingapp.data.entity.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RestaurantApiService {

    @GET("restaurants")
    suspend fun getAllRestaurants(@Query("page") page:Int) : Response<RestaurantResponse>

    @GET("restaurants/{categoryName}")
    suspend fun getRestaurantsByCategory(@Path ("categoryName") categoryName : String) : Response<RestaurantByCategoryResponse>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest) : Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest) : Response<LoginResponse>

    @POST("order")
    suspend fun order(@Body order: Order) : Call<Void>

    @GET("order/{userId}")
    suspend fun getOrders(@Path("userId") userId: String) : Response<OrderResponse>


    @PUT("acc/address")
    suspend fun updateAddress(@Body addressRequest: AddressRequest) : Response<LoginResponse>

    //POST api/order -> accountId,meal,resturantName, quantity,orderDate
    //GET api/order/(id)
}