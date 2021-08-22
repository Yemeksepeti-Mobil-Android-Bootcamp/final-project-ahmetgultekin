package com.example.foodorderingapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.foodorderingapp.data.ApiRepository
import com.example.foodorderingapp.data.entity.LoginRequest
import com.example.foodorderingapp.data.entity.LoginResponse
import com.example.foodorderingapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val apiRepository: ApiRepository) :ViewModel(){

    fun login(loginRequest: LoginRequest) : LiveData<Resource<LoginResponse>>{
        return apiRepository.login(loginRequest)
    }
    fun saveUserInfo(user : LoginResponse){
        apiRepository.saveUserInfo(user)
    }
}