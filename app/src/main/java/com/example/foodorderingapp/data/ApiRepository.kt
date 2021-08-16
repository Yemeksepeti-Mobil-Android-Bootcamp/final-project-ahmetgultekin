package com.example.foodorderingapp.data

import com.example.foodorderingapp.data.local.LocalDataSource
import javax.inject.Inject

class ApiRepository @Inject constructor(val localDataSource: LocalDataSource) {

    fun isFirstLaunch() :Boolean {
        return localDataSource.isFirstLaunch()
    }
    fun saveSituation(situaiton:Boolean){
        localDataSource.saveSituation(situaiton)
    }
}