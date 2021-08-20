package com.example.foodorderingapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.foodorderingapp.data.ApiRepository
import com.example.foodorderingapp.data.entity.LoginResponse
import com.example.foodorderingapp.data.entity.RegisterRequest
import com.example.foodorderingapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val apiRepository: ApiRepository): ViewModel() {


    fun register(registerRequest: RegisterRequest):LiveData<Resource<LoginResponse>>{
        return apiRepository.register(registerRequest)
    }
    fun setToken(token : String){
        apiRepository.setToken(token)
    }
}