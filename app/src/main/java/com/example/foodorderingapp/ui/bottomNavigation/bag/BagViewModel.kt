package com.example.foodorderingapp.ui.bottomNavigation.bag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodorderingapp.data.ApiRepository
import com.example.foodorderingapp.data.entity.FoodItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BagViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
    private val bagItems = MutableLiveData<List<FoodItem>>()
    fun getBagItems():LiveData<List<FoodItem>>{
        bagItems.value = apiRepository.getBagItems()
        return bagItems
    }
}