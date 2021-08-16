package com.example.foodorderingapp.ui.onboarding

import androidx.lifecycle.ViewModel
import com.example.foodorderingapp.data.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(val apiRepository: ApiRepository): ViewModel() {

    fun saveSituation(situation : Boolean){
        apiRepository.saveSituation(situation)
    }
}