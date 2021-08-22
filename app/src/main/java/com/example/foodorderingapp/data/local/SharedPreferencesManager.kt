package com.example.foodorderingapp.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {
    private var sharedPreferences : SharedPreferences? = null
    companion object{
        const val IS_FIRST_LAUNCH = "com.example.foodordering.first_launch"
        const val IS_BASKET_EMPTY = "com.example.foodordering.empty_basket"
        const val TOKEN = "com.example.foodordering.token"
    }
    init {
         sharedPreferences = context.getSharedPreferences("sharedPreferencesUtil", Context.MODE_PRIVATE)
    }

    fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences?.let {
            val editor = it.edit()
            editor.putBoolean(key, value)
            editor.apply()
        }
    }

    fun saveFirstLaunch(situation:Boolean){
        saveBoolean(IS_FIRST_LAUNCH,situation)
    }
    fun getFirstLaunch() : Boolean{
        return sharedPreferences!!.getBoolean(IS_FIRST_LAUNCH,true)
    }

}