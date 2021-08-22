package com.example.foodorderingapp.ui.bottomNavigation.account

import androidx.lifecycle.ViewModel
import com.example.foodorderingapp.data.ApiRepository
import com.example.foodorderingapp.data.entity.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val apiRepository: ApiRepository):ViewModel(){


    fun getUserInfo() : LoginResponse?{
        return apiRepository.getUserInfo()
    }
    fun deleteBag(){
        apiRepository.deleteBag()
    }
    fun deleteUser(){
        apiRepository.deleteUser()
    }

}