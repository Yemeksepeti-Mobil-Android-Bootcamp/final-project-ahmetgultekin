package com.example.foodorderingapp.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodorderingapp.data.ApiRepository
import com.example.foodorderingapp.data.entity.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(val apiRepository: ApiRepository) : ViewModel() {

    private val firstLaunch = MutableLiveData<Boolean>()
    fun isFirstLaunch():LiveData<Boolean> {
        firstLaunch.value = apiRepository.isFirstLaunch()
        return firstLaunch
    }
    fun getUserInfo() : LoginResponse?{
        return apiRepository.getUserInfo()
    }
}