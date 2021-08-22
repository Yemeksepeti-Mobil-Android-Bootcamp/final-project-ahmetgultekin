package com.example.foodorderingapp.ui.bottomNavigation.restaurant.foodDetail

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
    private val quantity = MutableLiveData<Int>()

    init {
        quantity.value = 1
    }

    fun setFood(foodItem: FoodItem){
        food.value = foodItem
    }
    fun getFood():LiveData<FoodItem>{
        return food
    }
    private val situation = MutableLiveData<Boolean>()

    fun addToBag(bagItem: BagItem){
        apiRepository.addToBag(bagItem)
    }

    fun increase(){
        quantity.value = quantity.value!!.plus(1)
    }
    fun decrease(){
        if(quantity.value!! > 1)
        quantity.value = quantity.value!!.minus(1)
    }
    fun getQuantity():LiveData<Int>{
        return quantity
    }
}