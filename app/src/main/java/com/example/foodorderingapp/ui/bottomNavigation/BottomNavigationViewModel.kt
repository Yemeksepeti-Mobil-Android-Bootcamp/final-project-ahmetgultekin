package com.example.foodorderingapp.ui.bottomNavigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodorderingapp.data.ApiRepository
import com.example.foodorderingapp.data.entity.FoodItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomNavigationViewModel @Inject constructor(val apiRepository: ApiRepository) : ViewModel() {
    private val bagItems = MutableLiveData<List<FoodItem>>()

    private val situation = MutableLiveData<Boolean>()
    fun basketSituation(situation: Boolean){
        apiRepository.basketSituation(situation)
    }
    fun isBasketEmpty() : LiveData<Boolean>{
        situation.value = apiRepository.isBasketEmpty()
        return situation
    }
    fun getBagItems():LiveData<List<FoodItem>>{
        bagItems.value = apiRepository.getBagItems()
        return bagItems
    }
}