package com.example.foodorderingapp.ui.bottomNavigation.restaurant.restaurantDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodorderingapp.data.entity.FoodItem
import com.example.foodorderingapp.databinding.FragmentRestaurantDetailBinding
import com.example.foodorderingapp.utils.IFoodListener

class RestaurantDetailFragment: Fragment(),IFoodListener {
    private lateinit var binding : FragmentRestaurantDetailBinding
    private val args: RestaurantDetailFragmentArgs by navArgs()
    private val viewModel : RestaurantDetailViewModel by viewModels()
    //Dummy Data
    private val menu = ArrayList<FoodItem>()
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
        initViews()
    }

    fun initViews(){

        val adapter = MenuAdapter()
        val arrayList = ArrayList<FoodItem>(args.clickedRestaurant.menu)
        adapter.setFoods(arrayList)
        adapter.setListener(this)
        binding.restaurantMenuRecycler.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.restaurantMenuRecycler.adapter = adapter
        viewModel.setClickedRestaurant(args.clickedRestaurant)
        viewModel.getClickedRestaurant().observe(viewLifecycleOwner,{
            binding.restaurantDetailName.text = it.name
            binding.restaurantDetailCategory.text  = it.category.categoryName
            binding.restaurantDetailCity.text = it.address
            Glide.with(requireContext()).load(it.imageUrl).into(binding.restaurantDetailImage)
        })
    }

    override fun onClick(foodItem: FoodItem) {
        val action = RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToFoodDetailFragment(foodItem,args.clickedRestaurant.name)
        findNavController().navigate(action)
    }

}