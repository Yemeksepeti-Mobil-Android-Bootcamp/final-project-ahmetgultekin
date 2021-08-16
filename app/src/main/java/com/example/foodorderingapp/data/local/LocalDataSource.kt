package com.example.foodorderingapp.data.local

import javax.inject.Inject

class LocalDataSource @Inject constructor(val sharedPreferencesManager: SharedPreferencesManager) {

    fun isFirstLaunch() : Boolean{
        return sharedPreferencesManager.getFirstLaunch()
    }

    fun saveSituation(situation : Boolean){
        sharedPreferencesManager.saveFirstLaunch(situation)
    }
}