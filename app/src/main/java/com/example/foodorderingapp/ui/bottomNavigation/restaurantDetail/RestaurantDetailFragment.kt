package com.example.foodorderingapp.ui.bottomNavigation.restaurantDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.foodorderingapp.databinding.FragmentRestaurantDetailBinding

class RestaurantDetailFragment: Fragment() {
    private lateinit var binding : FragmentRestaurantDetailBinding
    val args: RestaurantDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRestaurantDetailBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.v("Tag",args.clickedRestaurant.name)
    }


}