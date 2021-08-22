package com.example.foodorderingapp.ui.bottomNavigation.bag

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodorderingapp.data.ApiRepository
import com.example.foodorderingapp.data.entity.BagItem
import com.example.foodorderingapp.data.entity.LoginResponse
import com.example.foodorderingapp.data.entity.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class BagViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
    private val isSuccessful = MutableLiveData<Boolean?>()
    private val bagItems = MutableLiveData<List<BagItem>>()

    init {
        isSuccessful.value = null
    }
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
    fun deleteBag(){
        apiRepository.deleteBag()
    }

    fun getUserInfo() : LoginResponse?{
        return apiRepository.getUserInfo()
    }
     fun order(order: Order){
         viewModelScope.launch {
             isSuccessful.value = null
             try{
                 apiRepository.order(order)
                 isSuccessful.value = true
             }catch (e : Exception){
                 isSuccessful.value = false
             }

         }
    }
    fun isSuccessful(): LiveData<Boolean?>{
        Log.v("Tag","ViewModel: ${isSuccessful.value}")
        return isSuccessful
    }

}