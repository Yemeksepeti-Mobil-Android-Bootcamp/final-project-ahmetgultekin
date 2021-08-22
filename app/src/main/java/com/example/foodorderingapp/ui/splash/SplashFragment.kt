package com.example.foodorderingapp.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodorderingapp.R
import com.example.foodorderingapp.databinding.FragmentSplashBinding
import com.example.foodorderingapp.data.local.SharedPreferencesManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var _binding : FragmentSplashBinding
    private val viewModel : SplashViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater,container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isFirstLaunch().observe(viewLifecycleOwner,{
            when(it){
                true -> {Handler(Looper.getMainLooper()).postDelayed({
                    findNavController().navigate(R.id.action_splashFragment_to_onBoarding)
                }, 500)

                }
                false -> {
                    if(viewModel.getUserInfo() == null) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                        }, 500)

                    }
                    else{
                        Handler(Looper.getMainLooper()).postDelayed({
                            findNavController().navigate(R.id.action_splashFragment_to_bottomNavigationFragment)
                        }, 500)

                    }
                }
            }
        })





    }
}