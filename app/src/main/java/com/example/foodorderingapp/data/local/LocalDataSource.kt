package com.example.foodorderingapp.data.local

import com.example.foodorderingapp.data.entity.FoodItem
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val paperManager: PaperManager
    ) {

    fun isFirstLaunch() : Boolean{
        return sharedPreferencesManager.getFirstLaunch()
    }

    fun saveSituation(situation : Boolean){
        sharedPreferencesManager.saveFirstLaunch(situation)
    }

    fun basketSituation(situation: Boolean){
        sharedPreferencesManager.basketSet(situation)
    }
    fun isBasketEmpty() : Boolean{
        return sharedPreferencesManager.isBasketEmpty()
    }

    fun addToBag(foodItem: FoodItem){
        paperManager.addToBag(foodItem)
    }
    fun getBagItems():ArrayList<FoodItem>{
        return paperManager.getBagItems()
    }
}