package com.example.foodorderingapp.ui.bottomNavigation.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.R
import com.example.foodorderingapp.data.entity.RestaurantItem
import com.example.foodorderingapp.databinding.FragmentRestaurantBinding
import com.example.foodorderingapp.utils.IRestaurantListener

class RestaurantFragment : Fragment(),IRestaurantListener {
    private lateinit var binding : FragmentRestaurantBinding
    private val restaurant = ArrayList<RestaurantItem>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRestaurantBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    fun initViews(){
        restaurant.add(RestaurantItem("Pizza Max","","","Pizza,Burger","","","https://i.pinimg.com/originals/16/8e/91/168e918ec34bcf357051b427362a9d3a.jpg","","",""))
        restaurant.add(RestaurantItem("Pizza Max","","","Pizza,Burger","","","https://i.pinimg.com/originals/16/8e/91/168e918ec34bcf357051b427362a9d3a.jpg","","",""))
        restaurant.add(RestaurantItem("Pizza Max","","","Pizza,Burger","","","https://i.pinimg.com/originals/16/8e/91/168e918ec34bcf357051b427362a9d3a.jpg","","",""))
        restaurant.add(RestaurantItem("Pizza Max","","","Pizza,Burger","","","https://i.pinimg.com/originals/16/8e/91/168e918ec34bcf357051b427362a9d3a.jpg","","",""))
        val adapter = RestaurantAdapter()
        adapter.setRestaurants(restaurant)
        binding.restaurantRecycler.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.restaurantRecycler.adapter = adapter
        adapter.setListener(this)
    }

    override fun onClick(restaurantItem: RestaurantItem) {
        val action = RestaurantFragmentDirections.actionRestaurantFragmentToRestaurantDetailFragment(restaurantItem)
        findNavController().navigate(action)
    }
}