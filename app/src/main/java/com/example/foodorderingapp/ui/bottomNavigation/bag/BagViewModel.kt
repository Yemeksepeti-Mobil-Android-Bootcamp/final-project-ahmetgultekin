package com.example.foodorderingapp.ui.bottomNavigation.bag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodorderingapp.data.ApiRepository
import com.example.foodorderingapp.data.entity.BagItem
import com.example.foodorderingapp.data.entity.FoodItem
import com.example.foodorderingapp.data.entity.LoginResponse
import com.example.foodorderingapp.data.entity.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BagViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
    private val isSuccesful = MutableLiveData<Boolean>()
    private val bagItems = MutableLiveData<List<BagItem>>()
    fun getBagItems():LiveData<List<BagItem>>{
        bagItems.value = apiRepository.getBagItems()
        return bagItems
    }
    fun removeBagItem(bagItem: BagItem){
        apiRepository.removeBagItem(bagItem)
    }
    fun addToBag(bagItem: BagItem){
        apiRepository.addToBag(bagItem)
    }

    fun getUserInfo() : LoginResponse?{
        return apiRepository.getUserInfo()
    }
     fun order(order: Order) : LiveData<Boolean>{
         viewModelScope.launch { isSuccesful.value = apiRepository.order(order).value }
        return isSuccesful
    }

}