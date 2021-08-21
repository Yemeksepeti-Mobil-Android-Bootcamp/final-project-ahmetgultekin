package com.example.foodorderingapp.data

import androidx.lifecycle.LiveData
import com.example.foodorderingapp.data.entity.*
import com.example.foodorderingapp.data.local.LocalDataSource
import com.example.foodorderingapp.data.remote.RemoteDataSource
import com.example.foodorderingapp.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
    ) {

    fun isFirstLaunch() :Boolean {
        return localDataSource.isFirstLaunch()
    }
    fun saveSituation(situaiton:Boolean){
        localDataSource.saveSituation(situaiton)
    }
    fun basketSituation(situation: Boolean){
        localDataSource.basketSituation(situation)
    }
    fun isBasketEmpty() : Boolean{
        return localDataSource.isBasketEmpty()
    }
    fun addToBag(bagItem: BagItem){
        localDataSource.addToBag(bagItem)
    }
    fun getBagItems():ArrayList<BagItem>{
        return localDataSource.getBagItems()
    }
    fun removeBagItem(bagItem: BagItem){
        localDataSource.removeBagItem(bagItem)
    }
    fun setToken(token : String ){
        localDataSource.setToken(token)
    }
    fun getToken(): String?{
        return localDataSource.getToken()
    }
    fun saveUserInfo(user : LoginResponse){
        localDataSource.saveUserInfo(user)
    }
    fun getUserInfo() : LoginResponse?{
        return localDataSource.getUserInfo()
    }

    fun getAllRestaurants() = performNetworkOperation { remoteDataSource.getAllRestaurants() }

    fun getRestaurantByCategory(categoryName : String) = performNetworkOperation { remoteDataSource.getRestaurantsByCategory(categoryName) }

    fun login(loginRequest: LoginRequest) = performNetworkOperation { remoteDataSource.login(loginRequest) }

    fun register(registerRequest: RegisterRequest) = performNetworkOperation { remoteDataSource.register(registerRequest) }

    fun getOrders(userId: String) = performNetworkOperation { remoteDataSource.getOrders(userId) }

    fun order(order: Order):LiveData<Boolean> {
        return remoteDataSource.order(order)
    }
}