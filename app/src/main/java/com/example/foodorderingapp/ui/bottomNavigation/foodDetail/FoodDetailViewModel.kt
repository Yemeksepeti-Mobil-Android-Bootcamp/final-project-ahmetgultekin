package com.example.foodorderingapp.ui.bottomNavigation.foodDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodorderingapp.data.ApiRepository
import com.example.foodorderingapp.data.entity.BagItem
import com.example.foodorderingapp.data.entity.FoodItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(private val apiRepository: ApiRepository): ViewModel() {

    private val food = MutableLiveData<FoodItem>()

    fun setFood(foodItem: FoodItem){
        food.value = foodItem
    }
    fun getFood():LiveData<FoodItem>{
        return food
    }
    private val situation = MutableLiveData<Boolean>()
    fun basketSituation(situation:Boolean){
        apiRepository.basketSituation(situation)
    }
    fun isBasketEmpty() : LiveData<Boolean> {
        situation.value = apiRepository.isBasketEmpty()
        return situation
    }
    fun addToBag(bagItem: BagItem){
        apiRepository.addToBag(bagItem)
    }
}