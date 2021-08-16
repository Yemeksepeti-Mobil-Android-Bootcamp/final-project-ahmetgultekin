package com.example.foodorderingapp.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodorderingapp.R
import com.example.foodorderingapp.databinding.FragmentSplashBinding
import com.example.foodorderingapp.ui.utils.SharedPreferencesUtil

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
        _binding.animationView.addAnimatorListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                SharedPreferencesUtil.initSharedPreferences(requireContext())
                when(SharedPreferencesUtil.getFirstLaunch()){
                    true -> findNavController().navigate(R.id.action_splashFragment_to_onBoarding)
                    false -> findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                }

            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })



    }
}