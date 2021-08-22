package com.example.foodorderingapp.ui.bottomNavigation.account.saveAddress

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.foodorderingapp.data.ApiRepository
import com.example.foodorderingapp.data.entity.AddressRequest
import com.example.foodorderingapp.data.entity.LoginResponse
import com.example.foodorderingapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedAddressViewModel @Inject constructor(private val apiRepository: ApiRepository): ViewModel() {

    fun saveUserInfo(user : LoginResponse){
        apiRepository.saveUserInfo(user)
    }
    fun getUserInfo():LoginResponse?{
        return apiRepository.getUserInfo()
    }
    fun updateAddress(addressRequest: AddressRequest): LiveData<Resource<LoginResponse>>{
        return apiRepository.updateAddress(addressRequest)
    }
}