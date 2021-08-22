package com.example.foodorderingapp.data.local

import com.example.foodorderingapp.data.entity.BagItem
import com.example.foodorderingapp.data.entity.FoodItem
import com.example.foodorderingapp.data.entity.LoginResponse
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

    fun addToBag(bagItem: BagItem){
        paperManager.addToBag(bagItem)
    }
    fun getBagItems():ArrayList<BagItem>{
        return paperManager.getBagItems()
    }
    fun removeBagItem(bagItem: BagItem){
        paperManager.removeBagItem(bagItem)
    }
    fun deleteBag(){
        paperManager.deleteBag()
    }
    fun deleteUser(){
        paperManager.deleteUser()
    }

    fun saveUserInfo(user: LoginResponse){
        paperManager.saveUserInfo(user)
    }
    fun getUserInfo():LoginResponse?{
        return paperManager.getUserInfo()
    }
}