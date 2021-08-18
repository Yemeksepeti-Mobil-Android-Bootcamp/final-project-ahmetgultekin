package com.example.foodorderingapp.data

import com.example.foodorderingapp.data.entity.FoodItem
import com.example.foodorderingapp.data.local.LocalDataSource
import javax.inject.Inject

class ApiRepository @Inject constructor(private val localDataSource: LocalDataSource) {

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
    fun addToBag(foodItem: FoodItem){
        localDataSource.addToBag(foodItem)
    }
    fun getBagItems():ArrayList<FoodItem>{
        return localDataSource.getBagItems()
    }
}